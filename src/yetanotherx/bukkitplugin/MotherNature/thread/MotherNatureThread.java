package yetanotherx.bukkitplugin.MotherNature.thread;

import org.bukkit.World;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNatureSettings;

public class MotherNatureThread implements Runnable {

    public boolean interrupted = false;
    private MotherNature parent;
    
    public static int rainSteps = 0; // 1 step = 5 seconds
    public static int rainIntSteps = 0;
    public static int thunderSteps = 0; // 1 step = 5 seconds
    public static int thunderIntSteps = 0;

    public MotherNatureThread(MotherNature parent) {

        this.parent = parent;
    }

    public void run() {



        while (true) {

            try {

                Thread.sleep(5000); //5 seconds
                
                if ((rainSteps * 5) >= MotherNatureSettings.rainInterval) { // 10 seconds

                    if (rainIntSteps == 0) {
                        for (World world : parent.getServer().getWorlds()) {
                            world.setStorm(true);
                            world.setThundering(false);
                        }
                        MotherNature.log.info("Server is now raining.");
                    }

                    if ((rainIntSteps * 5) >= MotherNatureSettings.rainLength) {
                        for (World world : parent.getServer().getWorlds()) {
                            world.setStorm(false);
                            world.setThundering(false);
                        }
                        MotherNature.log.info("Server is no longer raining.");
                        rainIntSteps = 0;
                        rainSteps = 0;
                    } else {
                        rainIntSteps++;
                    }


                    if ((thunderSteps * 5) >= MotherNatureSettings.thunderInterval) { // 10 seconds

                        if (thunderIntSteps == 0) {
                            for (World world : parent.getServer().getWorlds()) {
                                world.setThundering(true);
                            }
                            MotherNature.log.info("Server is now thundering.");
                        }

                        if ((thunderIntSteps * 5) >= MotherNatureSettings.thunderLength) {
                            for (World world : parent.getServer().getWorlds()) {
                                world.setThundering(false);
                            }
                            MotherNature.log.info("Server is no longer thundering.");
                            thunderIntSteps = 0;
                            thunderSteps = 0;
                        } else {
                            thunderIntSteps++;
                        }
                    } else {
                        for (World world : parent.getServer().getWorlds()) {
                            world.setThundering(false);
                        }
                        thunderSteps++;
                    }


                } else {
                    for (World world : parent.getServer().getWorlds()) {
                        world.setStorm(false);
                        world.setThundering(false);
                    }
                    rainSteps++;
                }



            } catch (InterruptedException ex) {
                break;
            }

            if (Thread.interrupted() || interrupted) {
                break;
            }

        }
    }
}
