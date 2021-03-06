/*
 * Copyright (c) 2015 Shervin Asgari
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package no.asgari.civilization.server.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import no.asgari.civilization.server.SheetName;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@ToString(of = "name")
@JsonTypeName("tile")
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"ownerId", "hidden", "used", "itemNumber"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tile implements Item, Image {
    @NotEmpty
    private String name;
    private String type;
    private String description;
    private boolean used;
    private boolean hidden = true;
    private String ownerId; // game_id or player_id (username)
    private String image;
    private SheetName sheetName;
    private int itemNumber;

    public Tile(String name) {
        this.name = name;
        this.used = false;
        this.hidden = true;
    }

    @Override
    public SheetName getSheetName() {
        return sheetName = SheetName.TILES;
    }

    @Override
    public String revealPublic() {
        return getClass().getSimpleName() + " " + name;
    }

    @Override
    public String revealAll() {
        return revealPublic();
    }

    @Override
    public int compareTo(Spreadsheet o) {
        return getSheetName().compareTo(o.getSheetName());
    }

    @JsonGetter("image")
    @Override
    public String getImage() {
        image = getClass().getSimpleName().toLowerCase() + name + PNG;
        return image.replaceAll(" ", "");
    }
}
