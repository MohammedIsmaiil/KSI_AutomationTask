import com.microsoft.playwright.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import pages.HomePage;
import utils.PlaywrightFactory;
import utils.TestNGListener;

import java.util.Properties;


@Epic("Automation Exercise using Playwright - KSI")
@Feature("Search Function")
@Story("Search with keywords and Category")
@Listeners(TestNGListener.class)
public class SearchTests {

    private PlaywrightFactory playwrightFactory;
    private Page page;
    private Properties properties;
    private final String keyword = "lenovo laptops";
    private final String keywordFilter = "laptops";
    private final String category = "Electronics";


    @BeforeMethod(description = "Setup browser instance")
    public void setupBrowser() {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.init_properties();
        page = playwrightFactory.initBrowser(properties);
    }

    @Description("A user search with a keyword in the search field, and validate correct navigation and results")
    @Test(description = "Search with keyword in the search field ")
    void searchWithKeyword() {
        new HomePage(page).useSearchFieldWithKeyword(keyword)
                .verifySuccessfulSearchWithKeyword(keyword);
    }

    @Description("A user search with a category in the search field, and validate correct navigation and results related to this category")
    @Test(description = "Search with selecting a category from dropdown menu in the search field ")
    void searchWithCategory() {
        new HomePage(page).useSearchFieldWithCategory(category)
                .verifySuccessfulSearchWithCategory(category);
    }

    @Description("A user search with a keyword in the search field, and sort data from Low to High and validate the data displayed.")
    @Test(description = "Search with selecting a sorting option from dropdown menu search page.")
    void searchWithSortingLowToHighResults() {
        new HomePage(page).useSearchFieldWithKeyword(keyword)
                .sortProductLowToHigh()
                .verifySuccessfulSearchResultsSorting("LOW_TO_HIGH");
    }

    @Description("A user search with a keyword in the search field, and sort data from High to Low and validate the data displayed.")
    @Test(description = "Search with selecting a sorting option from dropdown menu search page.")
    void searchWithSortingHighToLowResults() {
        new HomePage(page).useSearchFieldWithKeyword(keyword)
                .sortProductHighToLow()
                .verifySuccessfulSearchResultsSorting("HIGH_TO_LOW");

    }

    @Description("A user search with a keyword in the search field, and sort data with AVG customer reviews and validate the data displayed.")
    @Test(description = "Search with selecting a sorting option from dropdown menu search page.")
    void searchWithSortingAVGResults() {
        new HomePage(page).useSearchFieldWithKeyword(keyword)
                .sortProductAvgReview()
                .verifySuccessfulSearchResultsSorting("AVG");
    }

    @Description("A user search with a keyword in the search field, and select a brand filter, Validate the data displayed.")
    @Test(description = "Search with selecting a filter option from search page.")
    void searchWitFilterBrand() {
        new HomePage(page).useSearchFieldWithKeyword(keywordFilter)
                .filterByBrand().verifySuccessfulSearchResultsFilteringByBrand();
    }

    @Description("A user search with a keyword in the search field, and select a free shipping filter, Validate the data displayed.")
    @Test(description = "Search with selecting free shipping filter option from search page.")
    void searchWitFilteringFreeShipping() {
        new HomePage(page).useSearchFieldWithKeyword(keywordFilter)
                .filterByFreeShipping().verifySuccessfulSearchResultsFilteringByFreeShipping();
    }

    @Description("A user search with a keyword in the search field, and select a range for prices, Validate the data displayed.")
    @Test(description = "Search with selecting range for products price from search page.")
    void searchWitFilteringRangePrice() {
        new HomePage(page).useSearchFieldWithKeyword(keywordFilter);
                page.pause();
    }

    @AfterMethod(description = "Tear down browser instance")
    public void tearDown() {
        page.context().browser().close();
    }


}
