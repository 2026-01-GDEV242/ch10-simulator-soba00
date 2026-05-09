import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple model of an owl.
 * Owls age, move, eat rabbits, and die.
 * 
 * ASSUMPTIONS:
 *  -Owls move and interact with the Field the same way all other animals do
 *  -Owls hunt rabbits using the same model as foxes
 *  -Owls have no direct interactions with wolves as they fly out of reach
 *  -Owls DO indirectly interact with foxes by competing for food
 *  -Uniquely Owls breed less often but have larger clutches of babies when 
 *   they do
 *  -Uniquely Owls Only hunt at at night to conserve energy
 *  -Uniquely Owls sometimes do not lose food level as they are constantly, 
 *   silently forage
 *  -Uniquely Owls get more energy from rabbits than foxes do as they use less
 *   calories when hunting due to smaller size and more effecient predation
 *  
 *  WITH THIS IN MIND, given the amount of hunger saving methods the owls 
 *  employ, even with their less common breeding and slower rabbit hunting 
 *  strategy; i assume the owls will outcompete the foxes for prey. As there
 *  are no other prey animals, i assume the rabbits will either survive or no 
 *  one does and thus im assuming the owls will either hunt them to extinction
 *  and starve themselves, or the rabbits will endure
 *
 * 
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (2)
 */
public class Owl extends Animal
{
    // Characteristics shared by all owl (class variables).
    
    // The age at which an owl can start to breed.
    private static final int BREEDING_AGE = 12;
    // The age to which an owl can live.
    private static final int MAX_AGE = 120;
    // The likelihood of an owl breeding.
    private static final double BREEDING_PROBABILITY = 0.04;
    // The maximum number of births.
    private static final int MAX_HATCH_SIZE = 3;
    // The food value of a single rabbit. In effect, this is the
    // number of steps an owl can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 11;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The owl's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create an owl. An owl can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the owl will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Owl(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
            setAge(rand.nextInt(getMaxAge()));
        }
        else {
   
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the owl does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * 
     * UNIQUE BEHAVIOR: due to their nocturnal nature and smaller, 
     * less calorie intensive bodies owls only hunt at night (50% of the time)
     * 
     * @param field The field currently occupied.
     * @param newOwls A list to return newly born owls.
     */
    public void act(List<Animal> newOwls)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newOwls);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(getAge() % 2 == 0) {
                newLocation = findFood();
            }
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    @Override
    protected int getMaxAge()
    {
        return MAX_AGE;
    }
    
    @Override
    protected int getBreedingAge()
    {
        return BREEDING_AGE;
    }
    
    /**
     * Make this owl more hungry. This could result in the owl's death.
     * 
     * UNIQUE BEHAVIOR: Due to their ability to fly totally silently 
     * owls often pick up tiny critters as "snacks" while they forage.
     * To simulate this, every turn there is a chance the owl snag a 
     * small "snack" to tide it over and its hunger will not tick down
     * 
     */
    private void incrementHunger()
    {
        //Silent hunters easily pick up small meals to tide them over
        if(rand.nextDouble()>.25) {
            foodLevel--;
        }
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this owl is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newOwls A list to return newly born owls.
     */
    private void giveBirth(List<Animal> newOwls)
    {
        // New owls are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Owl young = new Owl(false, field, loc);
            newOwls.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_HATCH_SIZE) + 1;
        }
        return births;
    }

    
}
