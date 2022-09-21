package idle.molaeng_back.review.repository;

import idle.molaeng_back.review.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review save(Review review);
    Review findByReviewId(long reviewId);
    List<Review> findByUserUserId(long userId);
    Slice<Review> findAllByUserUserId(long userId, Pageable pageable);

    void deleteByReviewId(long reviewId);

    Slice<Review> findAllByRecipeRecipeId(long recipeId, Pageable pageable);
    // 별점 높은순, sortNO= 0
    List<Review> findAllByRecipeRecipeIdOrderByScoreDesc(long recipeId);
    // 별점 낮은순, sortNo= 1
    List<Review> findAllByRecipeRecipeIdOrderByScore(long recipeId);
    // 최신순, sortNO=2
    List<Review> findAllByRecipeRecipeIdOrderByReviewDateDesc(long recipeId);

    // 좋아요 많은 순
    //쿼리를 못짜겠드아아아 일단 보류! 막내 마음대로 하랬다 뭐
    @Query(value = "select * from Review r left join review_like rl group by r Order by count(rl) where r.recipe_id = :recipeId", nativeQuery = true)
    List<Review> findAllByLikecount(@Param("recipeId") long recipeId);



}