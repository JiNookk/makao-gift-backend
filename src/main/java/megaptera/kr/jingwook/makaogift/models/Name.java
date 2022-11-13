package megaptera.kr.jingwook.makaogift.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Name {
    @Column(name = "name")
    private String value;

    public Name() {
    }

    public Name(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
