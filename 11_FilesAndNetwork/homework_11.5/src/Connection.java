public class Connection {

  private Station stationOn;
  private Station stationTo;
  private Line lineOn;
  private Line lineTo;


  public Connection(Station stationOn, Station stationTo, Line lineOn, Line lineTo) {
    this.stationOn = stationOn;
    this.stationTo = stationTo;
    this.lineOn = lineOn;
    this.lineTo = lineTo;
  }

  public void setStationOn(Station stationOn) {
    this.stationOn = stationOn;
  }

  public void setStationTo(Station stationTo) {
    this.stationTo = stationTo;
  }

  public void setLineOn(Line lineOn) {
    this.lineOn = lineOn;
  }

  public void setLineTo(Line lineTo) {
    this.lineTo = lineTo;
  }

  public Station getStationOn() {
    return stationOn;
  }

  public Station getStationTo() {
    return stationTo;
  }

  public Line getLineOn() { return lineOn; }

  public Line getLineTo() {return lineTo; }

}
