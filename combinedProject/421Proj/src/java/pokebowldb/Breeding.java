/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jared
 */
@ManagedBean(name = "Breeding")
@SessionScoped
public class Breeding implements Serializable {
    
    String name;
    char gender;
    char shiny;
    String nature;
    String ability;
    String hp;
    String attack;
    String defense;
    String spAttack;
    String spDefense;
    String speed;
    String move1;   
    String move2;
    String move3;
    String move4;
    PreparedStatement ps;
    ResultSet result;
    List<BreedingProject> projects;

    
    public void getResults() throws SQLException {
        if(CreateConnection.con == null) {
            CreateConnection.getConnection();
        }
        boolean first = true;
        int nameIndex = -1;
        int genderIndex = -1;
        int shinyIndex = -1;
        int natureIndex = -1;
        int abilityIndex = -1;
        int hpIndex = -1;
        int attackIndex = -1;
        int defenseIndex = -1;
        int spAttackIndex = -1;
        int spDefenseIndex = -1;
        int speedIndex = -1;
        int move1Index = -1;   
        int move2Index = -1;
        int move3Index = -1;
        int move4Index = -1;
        int currentIndex = 1;
        //String query = "select \"Pokemon\".*, \"Discord_Name\", \"Friend_Code\" from \"Pokemon\", \"Player\" ";
        String query = "select P.*, \"Discord_Name\", \"Friend_Code\" from (select \"Name\", \"Gender\", \"Shiny\", \"Nature\", \"Ability\", \"HP\", \"Attack\", \"Defense\", \"Special_Attack\", \"Special_Defense\", \"Speed\", \"Move1\", \"Move2\", \"Move3\", \"Move4\", \"Owner\" from \"Pokemon\" ";
        
        
        if(!name.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Name\")=?";
            nameIndex = currentIndex;
            currentIndex++;
        }
        if(gender != '\0') {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Gender\")=?";
            genderIndex = currentIndex;
            currentIndex++;
        }
        if(shiny != '\0') {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Shiny\")=?";
            shinyIndex = currentIndex;
            currentIndex++;
        }
        if(!nature.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Nature\")=?";
            natureIndex = currentIndex;
            currentIndex++;
        }
        if(!ability.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Ability\")=?";
            abilityIndex = currentIndex;
            currentIndex++;
        }
        if(isInt(hp)) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "\"HP\"=?";
            hpIndex = currentIndex;
            currentIndex++;
        }
        if(isInt(attack)) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "\"Attack\"=?";
            attackIndex = currentIndex;
            currentIndex++;
        }
        if(isInt(defense)) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "\"Defense\"=?";
            defenseIndex = currentIndex;
            currentIndex++;
        }
        if(isInt(spAttack)) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "\"Special_Attack\"=?";
            spAttackIndex = currentIndex;
            currentIndex++;
        }
        if(isInt(spDefense)) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "\"Special_Defense\"=?";
            spDefenseIndex = currentIndex;
            currentIndex++;
        }
        if(isInt(speed)) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "\"Speed\"=?";
            speedIndex = currentIndex;
            currentIndex++;
        }
        if(!move1.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Move1\")=?";
            move1Index = currentIndex;
            currentIndex++;
        }
        if(!move2.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Move2\")=?";
            move2Index = currentIndex;
            currentIndex++;
        }
        if(!move3.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Move3\")=?";
            move3Index = currentIndex;
            currentIndex++;
        }
        if(!move4.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Move4\")=?";
            move4Index = currentIndex;
            currentIndex++;
        }
        query += ") P, \"Player\" where P.\"Owner\"=\"Player\".\"Friend_Code\"";
        ps = CreateConnection.con.prepareStatement(query);
        //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
        if(nameIndex != -1) {
            ps.setString(nameIndex, name.toUpperCase());
        }
        if(genderIndex != -1) {
            ps.setString(genderIndex, Character.toString(Character.toUpperCase(gender)));
        }
        if(shinyIndex != -1) {
            ps.setString(shinyIndex, Character.toString(Character.toUpperCase(shiny)));
        }
        if(natureIndex != -1) {
            ps.setString(natureIndex, nature.toUpperCase());
        }
        if(abilityIndex != -1) {
            ps.setString(abilityIndex, ability.toUpperCase());
        }
        if(hpIndex != -1) {
            ps.setString(hpIndex, hp.toUpperCase());
        }
        if(attackIndex != -1) {
            ps.setString(attackIndex, attack);
        }
        if(defenseIndex != -1) {
            ps.setString(defenseIndex, defense);
        }
        if(spAttackIndex != -1) {
            ps.setString(spAttackIndex, spAttack);
        }
        if(spDefenseIndex != -1) {
            ps.setString(spDefenseIndex, spDefense);
        }
        if(speedIndex != -1) {
            ps.setString(speedIndex, speed);
        }
        if(move1Index != -1) {
            ps.setString(move1Index, move1.toUpperCase());
        }
        if(move2Index != -1) {
            ps.setString(move2Index, move2.toUpperCase());
        }
        if(move3Index != -1) {
            ps.setString(move3Index, move3.toUpperCase());
        }
        if(move4Index != -1) {
            ps.setString(move4Index, move4.toUpperCase());
        }
        
        
        result = ps.executeQuery();
        projects = new ArrayList<BreedingProject>();
        while(result.next()){
                BreedingProject bp = new BreedingProject();

                bp.setName(result.getString("name"));
                bp.setGender(result.getString("gender"));
                bp.setShiny(result.getString("shiny"));
                bp.setNature(result.getString("nature"));
                bp.setAbility(result.getString("ability"));
                bp.setHp(result.getInt("hp"));
                bp.setAttack(result.getInt("attack"));
                bp.setDefense(result.getInt("defense"));
                bp.setSpAttack(result.getInt("special_attack"));
                bp.setSpDefense(result.getInt("special_defense"));
                bp.setSpeed(result.getInt("speed"));
                bp.setMove1(result.getString("move1"));
                bp.setMove2(result.getString("move2"));
                bp.setMove3(result.getString("move3"));
                bp.setMove4(result.getString("move4"));
                bp.setFriendCode(result.getLong("friend_code"));
                bp.setDiscordName(result.getString("discord_name"));

                //store all data Stringo a List
                projects.add(bp);
        }
    }
    
    public List<BreedingProject> getProjects() {
        return projects;
        
    }
    
    public Breeding() {
        projects = new ArrayList<BreedingProject>();
    }
    
    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
}
    
    public void addBreeding() throws SQLException {
        if(CreateConnection.con == null) {
            CreateConnection.getConnection();
        }
        int rand = (int)(Math.random() * Integer.MAX_VALUE);
        String update1 = "insert into \"Pokemon\" values(?, ?, ?, ?, ?, ?, ?, 386680301688, ?, ?,?,?,?,?,?,?,?,?,?)";
        String update2 = "insert into \"Breeding\" values(?, 386680301688, 0)";
        ps = CreateConnection.con.prepareStatement(update1);
        
        ps.setInt(1, rand);
        
        ps.setString(2, name);
        
        ps.setString(3, "0");
        
        if(gender == 'M' || gender == 'F') {
            ps.setString(4, Character.toString(gender));
        }
        else {
            ps.setString(4, null);
        }
        if(shiny == 'y' || shiny == 'n') {
            ps.setString(5, Character.toString(shiny));
        }
        else {
            ps.setString(5, "n");
        }
        

        ps.setString(6, nature);
        ps.setString(7, ability);
        
        
        ps.setInt(8, 0);
        
        if(isInt(hp)) {
            ps.setString(9, hp);
        }
        else {
            ps.setString(9, null);
        }
        if(isInt(attack)) {
            ps.setString(10, attack);
        }
        else {
            ps.setString(10, null);
        }
        if(isInt(defense)) {
            ps.setString(11, defense);
        }
        else {
            ps.setString(11, null);
        }
        if(isInt(spAttack)) {
            ps.setString(12, spAttack);
        }
        else {
            ps.setString(12, null);
        }
        if(isInt(spDefense)) {
            ps.setString(13, spDefense);
        }
        else {
            ps.setString(13, null);
        }
        if(isInt(speed)) {
            ps.setString(14, speed);
        }
        else {
            ps.setString(14, null);
        }
        
        
        if(move1.equals("")) {
            ps.setString(15, null);
        }
        else {
            ps.setString(15, move1);
        }
        if(move2.equals("")) {
            ps.setString(16, null);
        }
        else {
            ps.setString(16, move2);
        }
        if(move3.equals("")) {
            ps.setString(17, null);
        }
        else {
        ps.setString(17, move3);
        }
        if(move4.equals("")) {
            ps.setString(18, null);
        }
        else {
        ps.setString(18, move4);
        }
        
        ps.executeUpdate();
        
        ps = CreateConnection.con.prepareStatement(update2);
        ps.setInt(1, rand);
        ps.executeUpdate();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getShiny() {
        return shiny;
    }

    public void setShiny(char shiny) {
        this.shiny = shiny;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(String spAttack) {
        this.spAttack = spAttack;
    }

    public String getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(String spDefense) {
        this.spDefense = spDefense;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getMove1() {
        return move1;
    }

    public void setMove1(String move1) {
        this.move1 = move1;
    }

    public String getMove2() {
        return move2;
    }

    public void setMove2(String move2) {
        this.move2 = move2;
    }

    public String getMove3() {
        return move3;
    }

    public void setMove3(String move3) {
        this.move3 = move3;
    }

    public String getMove4() {
        return move4;
    }

    public void setMove4(String move4) {
        this.move4 = move4;
    }
    
}
