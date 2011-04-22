package yetanotherx.bukkitplugin.MotherNature.thread;

import org.bukkit.World;
import yetanotherx.bukkitplugin.MotherNature.MotherNature;
import yetanotherx.bukkitplugin.MotherNature.MotherNatureSettings;

public class MotherNatureThread implements Runnable {

    public boolean interrupted = false;
    private MotherNature parent;

    public MotherNatureThread(MotherNature parent) {

        this.parent = parent;
    }

    public void run() {

        int rainSteps = 0; // 1 step = 5 seconds
        int rainIntSteps = 0;

        int thunderSteps = 0; // 1 step = 5 seconds
        int thunderIntSteps = 0;

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
