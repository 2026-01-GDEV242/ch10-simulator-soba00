# Ch10-Simulator
 
## Outline

 this project extends the "Fox and Rabbit" simulator by adding an "Owl" animal
 
 ---
 
## Changes Made

### Refactor (12.45-12.48 Book)

 Moved the following behavior to the Animal class:
 - location
 - age
 - death
 - breeding logic
 - movement
 
 this lowers coupling between itself, every animal subclass, and field while 
 increasing focus 

 ---
 
 ### Added Owl (12.51 Book)
 
 Added the 'Owl' subclass which extends Animal
 Owl is based off fox with modified hunting efficency behaviors
 
 
 ---
 
  
## Owl Behavior

 Owl follows Fox's behavior for:
 - movement
 - hunting rabbits
 - breeding
 - death and aging

 Owl had unique behavior for:
 - Nocturnal Hunting: Owls will only move at night (dont move on odd steps)
 - Lower Breeding Rate: Owls breed slower but have larger clutches, 
 making them start slow and ramp up
 - Silent Hunter: Owls may occasionally (p=.25) will tide themselves over by 
 "foraging" thanks to their silent flying instead of loosing food level.
 
  
  