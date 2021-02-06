package biz.uoray.api.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoreForm {

    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
