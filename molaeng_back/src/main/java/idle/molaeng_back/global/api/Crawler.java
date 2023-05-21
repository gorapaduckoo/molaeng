package idle.molaeng_back.global.api;


import idle.molaeng_back.recipe.model.entity.Ingredient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class Crawler {

    // 외부에서 크롤링 사이트를 수정할 수 없도록 private로 선언하는게 좋지않을까
    private final String URL = "https://www.ssg.com/search.ssg?target=all&query=";
    private String query;

    public double getIngredientPrice(Ingredient ingredient) {
        Elements elements = getElementsFromSite(ingredient.getIngredientName());
        if(elements==null) return 0;
        return getAveragePrice(elements, ingredient);
    }

    private Elements getElementsFromSite(String ingredientName) {
        Document document = null;
        try {
            query = URLEncoder.encode(ingredientName, "UTF-8");
            document = Jsoup.connect(URL+query)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36 Edg/105.0.1343.53").timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(document==null) return null;
        return document.select("#divProductImg .cunit_info");
    }

    private double getAveragePrice(Elements elements, Ingredient ingredient) {
        int itemSize = Math.min(elements.size(), 8);

        int itemCnt = 0;
        double priceSum = 0;
        for (int i=0; i<itemSize; i++) {
            Element e = elements.get(i);
            double itemPrice = getItemPricePerGram(e, ingredient);
            if(itemPrice==0) continue;
            priceSum += itemPrice;
            itemCnt++;
        }

        return Math.round((priceSum/(double)itemCnt)*10)/10.0;
    }

    private double getItemPricePerGram(Element e, Ingredient ingredient) {
        String itemName = e.select(".title .tx_ko").text();
        String pricePart = e.select(".cunit_prw .unit").text();

        String[] blockWords = ingredient.getIngredientBlockWord().split(" ");
        String ingredientUnit = ingredient.getIngredientUnit();

        if(hasBlockedKeword(itemName, blockWords) || pricePart.equals("")) return 0;

        String priceStr = pricePart.substring(pricePart.indexOf(":")+1, pricePart.indexOf("원"));
        double price = Double.parseDouble(priceStr.replaceAll("[^0-9]", ""));

        // unitPart: 100g
        // unit: g, weight: 100
        String unitPart = pricePart.substring(pricePart.indexOf("(")+1, pricePart.indexOf('당'));
        String unit = unitPart.replaceAll("[0-9]","");
        int weight = Integer.parseInt(unitPart.replaceAll("[^0-9]", ""));

        if(!unit.equals(ingredientUnit)) {
            if(unit.equals("kg")) {
                weight*=1000;
                unit = "g";
            } else if (unit.equals("개")) {
                price /= ingredient.getIngredientWeight();
                unit = "g";
            } else {
                System.out.println(ingredient.getIngredientId() + "번 재료 " + ingredient.getIngredientName() +": 사이트 내 단위 체크 필요");
                return 0;
            }
        }

        if(!unit.equals("g")&&!unit.equals("ml")) {
            System.out.println(ingredient.getIngredientId() + "번 재료 " + ingredient.getIngredientName() +": DB 내 단위 체크 필요");
            return 0;
        }
        price /= weight;
        return price;
    }

    private boolean hasBlockedKeword(String itemName, String[] blockWords) {
        boolean result = false;
        if(blockWords[0].equals("")) return result;

        for (String word : blockWords) {
            if (itemName.contains(word)) {
                result = true;
                break;
            }
        }
        return result;
    }

}
