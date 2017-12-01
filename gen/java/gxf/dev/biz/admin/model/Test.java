package gxf.dev.biz.admin.model;

import javax.persistence.*;

public class Test {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}