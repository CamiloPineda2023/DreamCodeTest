import model.Element;

import java.util.*;

public class Application {


    Set<String> categoriesNames = new HashSet<String>();
    Map<String, Element> elements = new HashMap<String, Element>();

    public static void main(String args[]) {

        Application app = new Application();
        Scanner scan = new Scanner(System.in);
        int input =  0;


        do {
            printMainOptions();
            try {
                input = Integer.parseInt(scan.nextLine());
            }

            catch (NumberFormatException e){
                System.out.println("please insert a number");

            }
            String name;
            String keywordsString;
            switch (input) {
                case 1:
                    System.out.println("please insert the name of the category");
                    name = scan.nextLine();
                    if (app.validateExist(name)) {
                        printCategoryAlreadyExistMessage();
                        break;

                    }
                    System.out.println("please insert the keywords of the category separated by comma");
                    keywordsString = scan.nextLine();


                    app.elements.put(name, Element.builder().name(name).keywords(getKeywordList(keywordsString)).isCategory(true).level(0).parent(null).build());
                    app.categoriesNames.add(name);
                    break;
                case 2:
                    System.out.println("please insert the name of the parent category");
                    name = scan.nextLine();
                    if (!app.validateExist(name)) {
                        printCategoryNotExistMessage();
                        break;

                    }

                    System.out.println("please insert the name of the category");
                    String categoryName = scan.nextLine();
                    if (app.validateExist(categoryName)) {
                        printCategoryAlreadyExistMessage();
                        break;

                    }

                    System.out.println("please insert the keywords of the subcategory separated by comma");
                    keywordsString = scan.nextLine();

                    Element parent = app.elements.get(name);
                    Element element = Element.builder().name(categoryName).keywords(getKeywordList(keywordsString)).isCategory(false).level(0).parent(parent).level(parent.getLevel() + 1).build();
                    app.elements.put(categoryName, element);
                    parent.getSubCategories().add(element);
                    app.categoriesNames.add(categoryName);
                    break;
                case 3:
                    System.out.println("please insert the name of the parent category or subcategory");
                    name = scan.nextLine();
                    if (!app.validateExist(name)) {
                        printCategoryNotExistMessage();
                        break;

                    }
                    System.out.println("the level of the category or subcategory is: " + app.elements.get(name).getLevel());
                    break;
                case 4:
                    System.out.println("please insert the name of the parent category or subcategory");
                    name = scan.nextLine();
                    if (!app.validateExist(name)) {
                        printCategoryNotExistMessage();
                        break;

                    }

                    System.out.println("The keyword of the category or subcategory including parent categories are:");

                    System.out.println(app.getKeywords(name));
                    break;
                default:
                    System.out.println("please insert a valid option");
                    break;


            }

            System.out.println("input");

        }

        while (input != 9);


    }

    public static void printCategoryOptions() {


    }



    public static void printMainOptions() {

        System.out.println("You have the option to add an new category or a subcategory");
        System.out.println("if you want to insert category press 1 or press 2 to insert a subcategory");
        System.out.println("if you want to get the level of a category or subcategory press 3");
        System.out.println("if you want to get the keywords of a category or subcategory press 4");
        System.out.println("press 9 to exit the application");


    }

    public static void printCategoryAlreadyExistMessage() {

        System.out.println("The category or a subcategory name already exist");


    }

    public static void printCategoryNotExistMessage() {

        System.out.println("The category or a subcategory not exist");


    }

    //Returns a keyword list from a given string
    public static List<String> getKeywordList(String keywords) {

        List<String> keywordsList = new ArrayList<String>();
        String array[] = keywords.split(",");
        keywordsList.addAll(Arrays.asList(array));
        return keywordsList;

    }

    //Validates if a category or subcategory name exists
    public boolean validateExist(String name) {

        return categoriesNames.contains(name);
    }

    //Get the keywords in String format of a given category or subcategory including the parents keywords
    public String getKeywords(String name) {
        Element currentElement = elements.get(name);
        StringBuilder builder = new StringBuilder();
        do {
            builder.append(currentElement.getKeywords());
            currentElement = currentElement.getParent();
        }
        while (currentElement != null);
        return builder.toString();
    }
}
