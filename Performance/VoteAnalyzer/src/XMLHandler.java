import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XMLHandler extends DefaultHandler {

    private static final int BATCH_SIZE = 1_000_000;
    private Voter voter;
    private static final SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter")) {
                if (voter == null) {
                    Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                    voter = new Voter(attributes.getValue("name"), birthDay);
                } else {
                    throw new RuntimeException("Nested <voter> elements are not allowed.");
                }
            } else if (qName.equals("visit")) {
                if (voter != null) {
                    DBConnection.countVoter(voter.getName(), birthDayFormat.format(voter.getBirthDay()));
                    if (DBConnection.getRecordCount() >= BATCH_SIZE) {
                        DBConnection.executeMultiInsert();
                        DBConnection.insertQueryAndRecordCountClear();
                    }
                } else {
                    throw new RuntimeException("Unexpected <visit> element without a corresponding <voter> element.");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SAXException(e.getMessage());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voter")) {
            voter = null;
        }
    }
}