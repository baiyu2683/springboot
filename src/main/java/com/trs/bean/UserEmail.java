package com.trs.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trs.system.Const;
import org.hibernate.bytecode.internal.javassist.FieldHandled;
import org.hibernate.bytecode.internal.javassist.FieldHandler;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by zhangheng on 16-7-26.
 */
@Entity
@Table(name="useremail")
public class UserEmail{// implements FieldHandled{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=255)
    private String name;
    @Column(length=255)
    private String email;

//    @Basic(fetch = FetchType.LAZY)
//    @Column(columnDefinition = "longtext")
//    private String doc;
//
//    @Basic(fetch = FetchType.LAZY)
//    @Column(columnDefinition = "longblob")
//    private byte[] headPic;
//
//    private FieldHandler fieldHandler;

    public UserEmail() {
    }

    public UserEmail(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getDoc() {
//        if(this.doc != null) {
//            return this.doc;
//        }
//
//        if (fieldHandler != null) {
//            return (String) fieldHandler.readObject(this, "doc", doc);
//        } else {
//            return null;
//        }
//    }

//    public void setDoc(String doc) {
//        this.doc = doc;
//    }
//
//    public byte[] getHeadPic() {
//        if(this.headPic != null) {
//            return this.headPic;
//        }
//
//        if (fieldHandler != null) {
//            return (byte[]) fieldHandler.readObject(this, "headPic", headPic);
//        } else {
//            return null;
//        }
//    }
//
//    public void setHeadPic(byte[] headPic) {
//        this.headPic = headPic;
//    }

    @Override
    public int hashCode(){
        return this.name.hashCode() + this.email.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof UserEmail)) return false;
        UserEmail ue = (UserEmail) o;
        return (this.getName() == null)? (ue.getName() == null): this.getName().equals(ue.getName()) &&
                (this.getEmail() == null)? (ue.getEmail() == null): this.getEmail().equals(ue.getEmail());
    }
//
//    @Override
//    public void setFieldHandler(FieldHandler fieldHandler) {
//        this.fieldHandler = fieldHandler;
//    }
//
//    @Override
//    public FieldHandler getFieldHandler() {
//        return this.fieldHandler;
//    }
}
