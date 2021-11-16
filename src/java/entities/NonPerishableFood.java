package entities;

import java.util.Objects;

public class NonPerishableFood extends Food {

    /**
     * Override the equals method to check content equality, rather than identity equality.
     * @return Boolean
     */
    @Override
    public boolean equals(Object obj){
        if (this==obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }

        NonPerishableFood other = (NonPerishableFood) obj;
        if (!Objects.equals(this.name, other.name)){
            return false;
        }else if (!Objects.equals(this.quantity, other.quantity)){
            return false;
        }else return Objects.equals(this.unit, other.unit);
    }

    /**
     * Construct a NonPerishableFood item, giving it the given name, quantity, and unit.
     * @param name of the NonPerishableFood item
     * @param quantity of the NonPerishableFood item
     * @param unit of measurement for the given quantity
     */
    public NonPerishableFood (String name, Double quantity, String unit) {
        super(name, quantity, unit);
    }
}