import model.Element;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ApplicationTest {
    Application app = new Application();
    @Before
    public void after(){


        app.categoriesNames = new HashSet<String>();
        app.categoriesNames.add("Furniture");
        app.categoriesNames.add("Electronics");
        app.categoriesNames.add("Home Appliances");

        app.categoriesNames.add("Minor Appliances");

        List<String> furnitureKeywords = new ArrayList<String>();
        furnitureKeywords.add("Chairs, Kitchen Furniture");

        List<String> electronicsKeywords = new ArrayList<String>();
        electronicsKeywords.add("Technology, Computers");

        List<String> homeAppliancesKeywords = new ArrayList<String>();
        homeAppliancesKeywords.add("Stoves, Microwaves");

        List<String> homeMinorAppliancesKeywords = new ArrayList<String>();
        homeMinorAppliancesKeywords.add("Minor Appliance Description");



        app.elements = new HashMap<String, Element>();
        app.elements.put("Furniture",Element.builder().isCategory(true).level(0).parent(null).keywords(furnitureKeywords).build());

        app.elements.put("Electronics",Element.builder().isCategory(true).level(0).parent(null).keywords(electronicsKeywords).build());

        Element homeAppliances = Element.builder().isCategory(true).level(0).parent(null).keywords(homeAppliancesKeywords).build();
        app.elements.put("Home Appliances",homeAppliances);

        app.elements.put("Minor Appliances",Element.builder().isCategory(true).level(0).parent(homeAppliances).keywords(homeMinorAppliancesKeywords).build());
    }




        @Test
        public void whenGetKeywordsOfASubcategoryIsCallThenReturnKeywordsOfParent() {


            assertTrue(app.getKeywords("Minor Appliances").contains("Stoves, Microwaves"));


        }



}
