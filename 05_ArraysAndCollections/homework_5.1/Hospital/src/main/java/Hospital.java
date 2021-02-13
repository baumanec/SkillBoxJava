public class Hospital {

  public static float[] generatePatientsTemperatures(int patientsCount) {

    float[] patientsTemperatures = new float[patientsCount];
    for (int i = 0; i < patientsCount; i++) {
      patientsTemperatures[i] = (float) Math.round((Math.random() * 8 + 32) * 10) / 10;
    }
    return patientsTemperatures;
  }

  public static String getReport(float[] temperatureData) {

    int healthy = 0;
    float sum = 0f;
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < temperatureData.length; i++) {
      builder.append(temperatureData[i]);
      if (i < temperatureData.length - 1) {
        builder.append(" ");
      }
      sum += temperatureData[i];
      if (temperatureData[i] >= 36.2f && temperatureData[i] <= 36.9f) {
        healthy++;
      }
    }
    float averageTemperature = (float) Math.round(sum / temperatureData.length * 100) / 100;
    String report =
        "Температуры пациентов: " + builder +
            "\nСредняя температура: " + averageTemperature +
            "\nКоличество здоровых: " + healthy;

    return report;
  }
}
