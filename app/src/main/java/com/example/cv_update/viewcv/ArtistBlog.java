package com.example.cv_update.viewcv;

import java.io.Serializable;

public class ArtistBlog implements Serializable {

    private String fullname;
    private String address;
    private String phone1;
    private String phone2;
    private String email;
    private String nationality;
    private String stateoforigin;
    private String dob;
    private String discipline;
    private String education;
    private String otherqualification;
    private String workingexperience;
    private String personalquality;
    private String language;
    private String ref;
    private String gender;


    public ArtistBlog() {

    }
    public ArtistBlog(String fullname, String address, String phone1, String phone2, String email, String nationality, String stateoforigin
    ,String dob, String discipline, String education, String otherqualification, String workingexperience, String personalquality,
                      String language, String ref, String gender){
        this.setFullname(fullname);
        this.setAddress(address);
        this.setPhone1(phone1);
        this.setPhone2(phone2);
        this.setEmail(email);
        this.setNationality(nationality);
        this.setStateoforigin(stateoforigin);
        this.setDob(dob);
        this.setDiscipline(discipline);
        this.setEducation(education);
        this.setOtherqualification(otherqualification);
        this.setWorkingexperience(workingexperience);
        this.setPersonalquality(personalquality);
        this.setLanguage(language);
        this.setRef(ref);
        this.setGender(gender);

    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStateoforigin() {
        return stateoforigin;
    }

    public void setStateoforigin(String stateoforigin) {
        this.stateoforigin = stateoforigin;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOtherqualification() {
        return otherqualification;
    }

    public void setOtherqualification(String otherqualification) {
        this.otherqualification = otherqualification;
    }

    public String getWorkingexperience() {
        return workingexperience;
    }

    public void setWorkingexperience(String workingexperience) {
        this.workingexperience = workingexperience;
    }

    public String getPersonalquality() {
        return personalquality;
    }

    public void setPersonalquality(String personalquality) {
        this.personalquality = personalquality;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
