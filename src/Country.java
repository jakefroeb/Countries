/**
 * Created by jakefroeb on 9/9/16.
 */
public class Country {
    private String name;
    private String abreviation;

    public Country(String name, String abreviation){
        this.name = name;
        this.abreviation = abreviation;
    }

    @Override
    public String toString(){
        return String.format("%s | %s\n",abreviation,name);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }
}
