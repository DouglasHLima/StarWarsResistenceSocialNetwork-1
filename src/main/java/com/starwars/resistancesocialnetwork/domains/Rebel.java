package com.starwars.resistancesocialnetwork.domains;

import com.starwars.resistancesocialnetwork.domains.enums.Gender;
import com.starwars.resistancesocialnetwork.domains.enums.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rebel {
    private Long id;
    private String name;
    private Integer age;
    private Gender gender;
    private Long headquarterId;
    private List<Item> inventory;
    @Builder.Default
    private Integer reports = 0;
    @Builder.Default
    private Boolean traitor = false;

    public void reportRebel(){
        this.reports++;
        this.traitorIdentifier();
    }

    private void traitorIdentifier(){
        if (this.reports>=3){
            this.traitor = true;
        }
    }
}
