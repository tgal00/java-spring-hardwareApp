package hr.tvz.gal.hardwareapp.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private ReviewService service;

    public ReviewController(ReviewService service){
        this.service = service;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return service.findAll();
    }

    @GetMapping(params = "code")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String code){
        return service.findAllReviewByCode(code);
    }
}
