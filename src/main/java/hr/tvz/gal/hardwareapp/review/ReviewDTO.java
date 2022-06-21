package hr.tvz.gal.hardwareapp.review;

public class ReviewDTO {

    private String title;
    private String text;
    private int rating;

    public ReviewDTO(String title, String text, int rating) {
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

}
