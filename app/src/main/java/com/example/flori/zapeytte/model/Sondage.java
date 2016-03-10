package com.example.flori.zapeytte.model;

import java.util.Date;

public class Sondage {

    private int idquestionnaire;
    private String pseudouser;
    private int idgroupe;
    private String nomquestionnaire;
    private boolean publiquequestionnaire;
    private String datefinquestionnaire;

    public Sondage(int idquestionnaire, String pseudouser, int idgroupe, String nomquestionnaire, boolean publiquequestionnaire, String datefinquestionnaire) {
        this.idquestionnaire = idquestionnaire;
        this.pseudouser = pseudouser;
        this.idgroupe = idgroupe;
        this.nomquestionnaire = nomquestionnaire;
        this.publiquequestionnaire = publiquequestionnaire;
        this.datefinquestionnaire = datefinquestionnaire;
    }

    public String toString() {
        return idquestionnaire +
                " - nomquestionnaire : '" + nomquestionnaire + '\'' +
                " - fin questionnaire : " + datefinquestionnaire +
                '}';
    }
}
