package model;

import lombok.*;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Element {

    boolean isCategory;
    List<Element> subCategories;
    int level;
    List<String> keywords;
    String name;
    Element parent;




}
