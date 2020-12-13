package web.homework3.demo.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String name = "";
    private String tele = "";
    private String post = "";
    private String addr = "";
    private String qq = "";

    public Person(){}
    public Person(String name, String tele, String post, String addr, String qq){
        this.name = name;
        this.tele = tele;
        this.post = post;
        this.addr = addr;
        this.qq = qq;
    }

    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }

    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTele() {
        return tele;
    }
    public void setTele(String tele) {
        this.tele = tele;
    }

    public Long getID() {
        return ID;
    }
}
