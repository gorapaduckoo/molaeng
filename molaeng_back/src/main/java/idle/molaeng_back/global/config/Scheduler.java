package idle.molaeng_back.global.config;

import idle.molaeng_back.global.api.Crawler;
import idle.molaeng_back.recipe.model.entity.Ingredient;
import idle.molaeng_back.recipe.model.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Scheduler {
    // 자정마다 실행 (cron = "초 분 시 일 월 요일 년(생략가능)")

    @Autowired
    private Crawler crawler;
    @Autowired
    private IngredientRepository ingredientRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateIngredientPrice() {
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        for (Ingredient i : ingredientList) {
            if(i.getIngredientIsCrawl()==0) continue;
            double newPrice = crawler.getIngredientPrice(ingredientRepository.findById(i.getIngredientId()));
            if(newPrice <= 0) continue;
            i.updatePrice(newPrice);

            try {
                Thread.sleep(3000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
