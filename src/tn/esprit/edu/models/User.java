package tn.esprit.edu.models;

public class User {
    private int idUser;
    private String nomUser;
    private String prenomUser;
    private int fonctionUser;
    private String login;
    private String motedepasse;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotedepasse() {
        return motedepasse;
    }

    public void setMotedepasse(String motedepasse) {
        this.motedepasse = motedepasse;
    }

    public int getFonctionUser() {
        return fonctionUser;
    }

    public void setFonctionUser(int fonctionUser) {
        this.fonctionUser = fonctionUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public User() {

    }
    public User(int id){
        this.idUser=id;
    }

//    public User(String nomUser, String prenomUser, int fonctionUser) {
//        this.nomUser = nomUser;
//        this.prenomUser = prenomUser;
//        this.fonctionUser = fonctionUser;
//    }
//
//    public User(int idUser, String nomUser, String prenomUser, int fonctionUser) {
//
//        this.idUser = idUser;
//        this.nomUser = nomUser;
//        this.prenomUser = prenomUser;
//        this.fonctionUser = fonctionUser;
//    }


    @Override
    public String toString() {
        return getPrenomUser();
    }

    public String isRole() {

        if (getFonctionUser() == 0) {
            return "admin";
        }
        if (getFonctionUser() == 1) {
            return "client";
        }
        if (getFonctionUser() == 2) {
            return "veterinaire";
        }
        return null;
    }
}
