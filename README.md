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

 
 ### Added Owl (12.51 Book)
 
 Added the 'Owl' subclass which extends Animal
 Owl is based off fox with modified hunting efficency behaviorsd
 
 
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
 
  
 ---
  
 
 ## Assumptions (from Owl.java header)
 - Owls move and interact with the Field the same way all other animals do
 - Owls hunt rabbits using the same model as foxes
 - Owls have no direct interactions with wolves as they fly out of reach
 - Owls DO indirectly interact with foxes by competing for food
 - Uniquely Owls breed less often but have larger clutches of babies when 
 they do
 - Uniquely Owls Only hunt at at night to conserve energy
 - Uniquely Owls sometimes do not lose food level as they are constantly, 
 silently forage
 - Uniquely Owls get more energy from rabbits than foxes do as they use less
 calories when hunting due to smaller size and more effecient predation
 
 
 ---
 
 
 ## Ecosystem Impacts
 
 ### Initial Assumptions (from Owl.java header)
 Given the amount of hunger saving methods the owls employ, even with their 
 less common breeding and slower rabbit hunting strategy; i assume the owls 
 will outcompete the foxes for prey. As thereare no other prey animals, i 
 assume the rabbits will either survive or no one does and thus im assuming 
 the owls will either hunt them to extinctionand starve themselves, or the 
 rabbits will endure.
 
 ### Updated Ecological Impact Observations
 My initial assuptions were bpoth right and wrong; While the owls did end up
 outlasting the foxes in every simulation, they started off slow and rappidly 
 overtook the foxes. I was however wrong in stating that it would be the owls
 out competeing the foxes and more the owls "outlasting" famine periods while
 while foxes outcompeted themselves out of existence. It seems their hunger 
 saving silent foraging + lower energy consumption resulted in them being more
 successful in the ecosystem
 
 