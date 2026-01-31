package bluenightfury46.dev.bloodMoon.json.random;

import bluenightfury46.dev.bloodMoon.ArmourData;
import bluenightfury46.dev.bloodMoon.BloodMoon;
import bluenightfury46.dev.bloodMoon.Moon;
import bluenightfury46.dev.bloodMoon.exceptions.FileNotFoundOrGenerated;
import bluenightfury46.dev.bloodMoon.json.JsonItemstack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Scanner;

public class MoonRandom {
    public static JsonEquipment jsonInit(File JSON_FILE) {

        try {
            if (JSON_FILE!=null){


                if(!JSON_FILE.exists()){
                    throw new FileNotFoundOrGenerated();
                }


                Scanner scan = new Scanner(JSON_FILE);

                String tempstr = "";
                while(scan.hasNextLine()){
                    tempstr+=scan.nextLine();
                }

                scan.close();

                if(tempstr.isEmpty()){
                    return new JsonEquipment( new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));
                }


                JsonEquipment data = new Gson().fromJson(tempstr, JsonEquipment.class);
                //JsonType test = new JsonType(JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM);

                return data;








                //END OF IF STATEMENT BY THE WAY (Java is impossible to read)
            }

        }
        catch(NullPointerException e){
            BloodMoon.plugin.getLogger().severe("JsonFileNullError, plugin shutting down");
            return new JsonEquipment( new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));
        }

        catch(FileNotFoundOrGenerated e){
            return new JsonEquipment( new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));

        }

        catch(ExceptionInInitializerError | FileNotFoundException e){
            return new JsonEquipment( new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));

        }


        return new JsonEquipment( new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR));
    }



    public static void jsonSave(JsonEquipment toJson, File JSON_FILE){

        try {
            if (JSON_FILE!=null){

                /*if(!JSON_FILE.canWrite()){
                    throw new ReadOnlyFileSystemException();
                }
*/
                if(!JSON_FILE.exists()){
                    JSON_FILE.createNewFile();
                }

                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                String str_data = gson.toJson(toJson);

                FileWriter writer = new FileWriter(JSON_FILE);

                writer.write(str_data);

                writer.close();





                //END OF IF STATEMENT BY THE WAY (Java is impossible to read)
            }

        }
        catch(NullPointerException e){
            BloodMoon.plugin.getLogger().severe("JsonFileNullError, plugin shutting down");
            // return new JsonData(JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.JSON_FILE_NULL);
        }

        catch(ReadOnlyFileSystemException e){
            BloodMoon.plugin.getLogger().severe("Json file is read only... Shutting down (How did you do that then?)");
            // return new JsonData(JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.JSON_FILE_READONLY);
        }

        catch (FileNotFoundException e) {
            JSON_FILE.mkdirs();

            //  return new JsonData(JsonData.HELMET_DEFAULT, JsonData.CHESTPLATE_DEFAULT, JsonData.LEGGINGS_DEFAULT, JsonData.BOOTS_DEFAULT, JsonData.JSON_FILE_EMPTY);
        } catch (IOException e) {
            BloodMoon.plugin.getLogger().severe("Failed to create JSON file...");
        }

        //  return new JsonData(JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.JSON_FILE_NULL);
    }


    public static final int ARRAYSTART = 0;


    public static ArmourData generateRandomEquipment(JsonEquipment equipmentpool, boolean efficient){

        if(equipmentpool.ITEM_POOL.size()<0){
            BloodMoon.plugin.getLogger().warning("Warning, the equipment pool size is < 0, if randomised equipment is enabled this is a fatal error... The plugin will now revert to the preset equipment loadout");
            return BloodMoon.data;
        }


        try{

            int MAX = equipmentpool.ITEM_POOL.size();

            if(!efficient) {

                int RANDOM_OFFHAND = Moon.getRandomNumber(ARRAYSTART, MAX);
                int RANDOM_MAINHAND = Moon.getRandomNumber(ARRAYSTART, MAX);
                int RANDOM_HELMET = Moon.getRandomNumber(ARRAYSTART, MAX);
                int RANDOM_CHESTPLATE = Moon.getRandomNumber(ARRAYSTART, MAX);
                int RANDOM_LEGGINGS = Moon.getRandomNumber(ARRAYSTART, MAX);
                int RANDOM_BOOTS = Moon.getRandomNumber(ARRAYSTART, MAX);


                return new ArmourData(equipmentpool.ITEM_POOL.get(RANDOM_HELMET).HELMET, equipmentpool.ITEM_POOL.get(RANDOM_CHESTPLATE).CHESTPLATE, equipmentpool.ITEM_POOL.get(RANDOM_LEGGINGS).LEGGINGS, equipmentpool.ITEM_POOL.get(RANDOM_BOOTS).BOOTS, equipmentpool.ITEM_POOL.get(RANDOM_MAINHAND).MAINHAND, equipmentpool.ITEM_POOL.get(RANDOM_OFFHAND).OFFHAND);
            } else{
                int RANDOM = Moon.getRandomNumber(ARRAYSTART, MAX);

                return (equipmentpool.ITEM_POOL.get(RANDOM));

            }





        }catch(NullPointerException ex){

        }

        return new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR);



    }





}
