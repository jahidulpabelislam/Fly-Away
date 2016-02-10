/**
 *  This class is the main class of the "Fly-Away" application. 
 *  "Fly-Away" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling, David J. Barnes, Robert Topp, 733474
 * @version 18.03.15
 */

public class Game
{
    private Parser parser;
    private Player thePlayer;
    private Room[] recentRooms;
    private Money money;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        thePlayer = new Player();
        recentRooms = new Room[2];
        createRooms();
        parser = new Parser();
        money = new Money(20);
    }

    /**
     * Create all the rooms and link their exits together.
     * Create items in rooms & in players backpack
     */
    private void createRooms()
    {
        Room outside, terminal, checkin, security, waiting, dutyfree, restaurants, boots, kfc, mcDonalds, boarding, plane;
        Item passport, car, ticket, luggage, handLuggage, tree, chicken, pepsi, burger, person, person2, 
             boardingPass, sweet, cola, glasses, crisp, sofa, bench, signBoard, bag, trolley, desk, trolley2;
        
        //create items in players backpack
        passport = new Item("passport", true, 1, Type.OBJECT, 0);
        thePlayer.putInBackPack(passport);
        ticket = new Item("ticket", true, 1, Type.OBJECT, 0);
        thePlayer.putInBackPack(ticket);
        luggage = new Item("luggage", true, 15, Type.OBJECT, 0);
        thePlayer.putInBackPack(luggage);
        handLuggage = new Item("hand-luggage", true, 10, Type.OBJECT, 0);
        thePlayer.putInBackPack(handLuggage);
        
        // create the rooms
        outside = new Room("outside the Main Entrance of the Airport");
        terminal = new Room("in the Terminal");
        checkin = new Room("in the Check-in area");
        security = new Room("in the Security area");
        waiting = new Room("in the Waiting area");
        dutyfree = new Room("in the Duty-free area");
        restaurants = new Room("in the Restaurant area");
        boots = new Room("in the store Boots");
        kfc = new Room("in KFC");
        mcDonalds = new Room("in McDonalds");
        boarding = new Room("in the Boarding area");
        plane = new Room("in the Plane");        
        
        // initialise room exits
        outside.setExit("north", terminal);       
        
        terminal.setExit("south", outside);
        terminal.setExit("east", checkin);
        terminal.setExit("west", security);

        checkin.setExit("west", terminal);

        security.setExit("west", waiting);
        
        waiting.setExit("north", restaurants);
        waiting.setExit("south", boarding);
        waiting.setExit("west", dutyfree);
        
        dutyfree.setExit("east", waiting);
        dutyfree.setExit("north", boots);
        
        boots.setExit("south", dutyfree);
        
        restaurants.setExit("south", waiting);
        restaurants.setExit("north", kfc);
        restaurants.setExit("east", mcDonalds);
        
        kfc.setExit("south", restaurants);
        
        mcDonalds.setExit("west", restaurants);
        
        boarding.setExit("north", waiting);
        boarding.setExit("west", plane);
        
        //create items in rooms
        tree = new Item("tree", false, 100, Type.OBJECT, 0);
        outside.putInRoom(tree);
        car = new Item("car", false, 75, Type.OBJECT, 0);
        outside.putInRoom(car);
        trolley = new Item("trolley", true, 15, Type.OBJECT, 0);
        outside.putInRoom(trolley);
        
        signBoard = new Item("sign-board", true, 75, Type.OBJECT, 0);
        terminal.putInRoom(signBoard);
        bag = new Item("bag", true, 1, Type.OBJECT, 0);
        terminal.putInRoom(bag);
        trolley2 = new Item("trolley", true, 15, Type.OBJECT, 0);
        terminal.putInRoom(trolley);
        
        boardingPass = new Item("boarding-pass", true, 1, Type.OBJECT, 0);
        checkin.putInRoom(boardingPass);
        person = new Item("person", true, 100, Type.OBJECT, 0);
        checkin.putInRoom(person);
        
        person2 = new Item("person", true, 100, Type.OBJECT, 0);
        security.putInRoom(person2);
        
        sofa = new Item("sofa", false, 50, Type.OBJECT, 0);
        waiting.putInRoom(sofa);
        
        chicken = new Item("chicken", true, 5, Type.FOOD, 2);
        kfc.putInRoom(chicken);
        pepsi = new Item("pepsi", true, 5, Type.DRINK, 0.99);
        kfc.putInRoom(pepsi);
        
        burger = new Item("burger", true, 5, Type.FOOD, 2);
        mcDonalds.putInRoom(burger);
        cola = new Item("cola", true, 5, Type.DRINK, 0.99);
        mcDonalds.putInRoom(cola);
        
        crisp = new Item("crisp", true, 1, Type.FOOD, 0.5);
        boots.putInRoom(crisp);
        sweet = new Item("sweet", true, 1, Type.FOOD, 0.5);
        boots.putInRoom(sweet);
        glasses = new Item("glasses", true, 1, Type.PRODUCT, 0.5);
        boots.putInRoom(glasses);
        
        desk = new Item("desk", false, 100, Type.OBJECT, 0);
        boarding.putInRoom(desk);
        
        thePlayer.setRoom(outside);  // starts game outside
        recentRooms[0] = outside;
        recentRooms[1] = outside;
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        Timer1 timer;
        
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean quit = false;
        boolean finished = false;
        boolean timeup = false;
        timer = new Timer1(240);
        while (! quit & !finished & !timeup) 
        {
            Command command = parser.getCommand();
            quit = processCommand(command);
            finished = finished();
            timeup = timer.getTimeUp();
        }
        System.out.println();
        if(finished)
        {
          System.out.println("Well Done! You have successfully got on your Plane, You have won the game!");
        }        
        else if(timeup)
        {
          System.out.println("Unlucky, You have lost the game. You have missed your plane, it has already taken off.");
        }
        System.out.println("Thank you for playing. Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("Welcome to Fly-Away!");
        System.out.println("Fly-Away is a game to succesfully board a plane.");
        System.out.println("You need to check-in then go through security then through boarding onto the plane.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printDetails();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) 
        {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case MOVE:
                move();
                break;
                
            case TURN:
                turn(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case PICK:
                pick(command);
                break;
                
            case GIVE:
                give(command);
                break;
                
            case BACK:
                back();
                break;
            
            case EAT:
                eat(command);
                break;
                
            case DRINK:
                drink(command);
                break;
                
            case BUY:
                buy(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander around at the Airport.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void move() 
    {
        Item passport, boardingPass, luggage;
        
        // Try to leave current room.
        Room nextRoom = thePlayer.getRoom().getExit(thePlayer.getDirection().toString());
        
        if (nextRoom == null) 
        {
            System.out.println("There is no door!");
            return;
        }
        
        passport = thePlayer.findItem("passport");
        boardingPass = thePlayer.findItem("boarding-pass");
        luggage = thePlayer.findItem("luggage");
        
        if((nextRoom.getShortDescription().contains("Security")
            || nextRoom.getShortDescription().contains("Plane")) 
            & (passport == null || boardingPass == null || luggage != null))
        {
          if(passport == null)
          {
            System.out.println("You need your passport."); 
          }
          if(boardingPass == null)
          {
            System.out.println("You need your boarding-pass."); 
          }
          if(luggage != null)
          {
            System.out.println("You need to give your luggage."); 
          }
        }
        else
        {
            thePlayer.setRoom(nextRoom);
            printDetails();
            recentRooms[1] = recentRooms[0];
            recentRooms[0] = thePlayer.getRoom();
        }        
    }

    /** 
     * "Turn" was entered. Check the rest of the command to see
     * whether we turn left or right.
     */
    private void turn(Command command)
    {
        if(!command.hasSecondWord()) 
        {
          // if there is no second word, we don't know which way to turn...
          System.out.println("turn which way?");
          return;
        }

        String direction = command.getSecondWord();
        
        if(direction.equals("left")) 
        { thePlayer.turnLeft();}
        else if(direction.equals("right")) 
        { thePlayer.turnRight();}
        else 
        { System.out.println("You can not turn that way");}
        System.out.println("You are currently facing " + thePlayer.getDirection());
    }

                  
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord())
        {
          System.out.println("Quit what?");
          return false;
        }
        {
          return true;  // signal that we want to quit
        }
    }
    
    /** 
     * "pick" was entered. 
     * Puts item in backpack if valid otherwise print error message.
     */
    private void pick(Command command)
    {
        Item passport, ticket;
      
        if(!command.hasSecondWord()) 
        {
          // if there is no second word, we don't know what to pick
          System.out.println("pick what?");
          return;
        }
        
        passport = thePlayer.findItem("passport");
        ticket = thePlayer.findItem("ticket");
        
        String object = command.getSecondWord();
        Item item = thePlayer.getRoom().findItem(object);
        
        if(item != null)
        {
          if(item.getPickable() == true)
          {
            if(item.getType() == Type.OBJECT)
            {
              if(item.getWeight() + thePlayer.getBackPackWeight() <= 50)
              {
                if(item.getName().equals("boarding-pass") & passport == null || ticket == null)
                {
                  System.out.println("You need your passport & ticket");
                }
                else
                {
                  thePlayer.putInBackPack(item);
                  int i = thePlayer.getRoom().getIndex(item);
                  thePlayer.getRoom().removeFromRoom(i);
                  System.out.println("You picked up " + object);
                }
              }
              else
              {
                System.out.println(object + " is not pickable, you are carrying to much already");
              }
            }
            else
            {
              System.out.println("You have to buy " + object);
            }
          }
          else
          {
            System.out.println(object + " is not pickable");
          }
        }
        else
        {
          System.out.println(object + " doesn't exist");
        }
    }
    
    /*
     * "give" was entered.
     * Put item in room if valid otherwise print error message.
     */
    private void give(Command command)
    {
        if(!command.hasSecondWord()) 
        {
          // if there is no second word, we don't know what to give
          System.out.println("give what?");
          return;
        }
        
        String object = command.getSecondWord();
        Item item = thePlayer.findItem(object);
        
        if(item != null)
        {
          thePlayer.getRoom().putInRoom(item);
          int i = thePlayer.getIndex(item);
          thePlayer.removeFromBackPack(i);
          System.out.println("You gave " + object);
        }        
        else
        {
          System.out.println(object + " doesn't exist");
        }
    }
    
    /*
     * Checks if player is in "Plane" then returns true otherwise returns false.
     */
    private boolean finished()
    {
        if(thePlayer.getRoom().getShortDescription().contains("Plane"))
        {
          return true;
        }
        return false; 
    }
    
    /*
     * "back" was entered.
     * Sets room to previous room.
     * Prints new details. Updates recentRoom array.
     */
    private void back()
    {
        thePlayer.setRoom(recentRooms[1]);
        printDetails();
        recentRooms[1] = recentRooms[0];
        recentRooms[0] = thePlayer.getRoom();
    }
    
    /*l
     * "eat" was entered.
     * if valid player "eats" food(removed from backpack). otherwise prints error message.
     */
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) 
        {
          // if there is no second word, we don't know what to give
          System.out.println("eat what?");
          return;
        }
        
        String object = command.getSecondWord();
        Item item = thePlayer.findItem(object);
        
        if(item != null)
        {
          if(item.getType() == Type.FOOD)
          {
            int i = thePlayer.getIndex(item);
            thePlayer.removeFromBackPack(i);
            System.out.println("You ate " + object);
          }
          else
          {
            System.out.println(object + " is not eatable");
          }
        }        
        else
        {
          System.out.println(object + " doesn't exist");
        }
    }
     
    /*
     * "drink" was entered.
     * if valid player "drinks" drink(removed from backpack). otherwise prints error message.
     */
    private void drink(Command command)
    {
        if(!command.hasSecondWord()) 
        {
          // if there is no second word, we don't know what to give
          System.out.println("drink what?");
          return;
        }
        
        String object = command.getSecondWord();
        Item item = thePlayer.findItem(object);
        
        if(item != null)
        {
          if(item.getType() == Type.DRINK)
          {
            int i = thePlayer.getIndex(item);
            thePlayer.removeFromBackPack(i);
            System.out.println("You drank " + object);
          }
          else
          {
            System.out.println(object + " is not drinkable");
          }
        }        
        else
        {
          System.out.println(object + " doesn't exist");
        }
    }
    /*
     * prints out details of current room (desctription, exits, items),
     * direction, backpack items & money.
     */
    private void printDetails()
    {
        System.out.println(thePlayer.getRoom().getLongDescription());
        System.out.println("You are facing " + thePlayer.getDirection());
        System.out.println(thePlayer.getBackPackItems());
        System.out.printf("You have £%.2f\n", money.getAmount());
    }
    
    /*
     * "buy" was entered.
     * If item is valid item is "brought" (added to backpack) 
     * otherwise prints out error message.
     */
    private void buy(Command command)
    {
        if(!command.hasSecondWord()) 
        {
          // if there is no second word, we don't know what to buy
          System.out.println("buy what?");
          return;
        }
        
        String object = command.getSecondWord();
        Item item = thePlayer.getRoom().findItem(object);
        
        if(item != null) //checks if item excits
        {
          if(item.getType() != Type.OBJECT)
          {
            if(item.getPickable() == true) //checks if item is carryable
            {
              if(item.getWeight() + thePlayer.getBackPackWeight() <= 50)
              {
                if(money.getAmount() - item.getCost() >= 0)
                {
                  money.takeMoney(item.getCost());
                  thePlayer.putInBackPack(item);
                  int i = thePlayer.getRoom().getIndex(item);
                  thePlayer.getRoom().removeFromRoom(i);
                  System.out.println("You brought " + object);
                  System.out.printf("You now have £%.2f\n", money.getAmount());
                }
                else
                {
                  System.out.println("You don't have enough money for " + object);
                }
              }
              else
              {
                System.out.println(object + " is not buyable, you are carrying to much already");
              }
            }
            else
            {
              System.out.println(object + " is not pickable"); 
            }
          }
          else
          {
            System.out.println(object + " is not buyable");
          }
        }
        else
        {
          System.out.println(object + " doesn't excits");
        }
    }
}