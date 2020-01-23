package model;

// Сам класс сериала с необходимыми полями и сеттерами
public class Serial {
    private String title;
    private int seasons;
    private int series;
    private int year;
    private int id;

    public Serial(int id, String title, int seasons, int series, int year) {
        this.id = id;
        this.title = title;
        this.seasons = seasons;
        this.series = series;
        this.year = year;
    }

    public Serial() {}

    public String save() {
        return "Сериал "  + '"' + title + '"' +" сохранён";
    }

    public String delete() {
        return "Сериал " + '"' + title + '"' + " удалён";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getSeasons() {
        return seasons;
    }

    public int getSeries() {
        return series;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return   "\n" + "Название: " + title + "\n" + "Количество сезонов: " + seasons + "\n" + "Количество серий: "  + series
                + "\n" + "Год выпуска: "+ year;
    }
}
