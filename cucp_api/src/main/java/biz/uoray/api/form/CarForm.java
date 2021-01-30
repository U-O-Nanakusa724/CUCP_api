package biz.uoray.api.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarForm {

    @NotBlank
    @Size(min = 1, max = 64)
    private String code;

    @Size(max = 64)
    private String name;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
