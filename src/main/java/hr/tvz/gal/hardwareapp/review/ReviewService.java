package hr.tvz.gal.hardwareapp.review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();

    List<ReviewDTO> findAllReviewByCode(String code);


}
