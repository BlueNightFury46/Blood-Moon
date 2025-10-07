package bluenightfury46.dev.bloodMoon;

import bluenightfury46.dev.bloodMoon.exceptions.FileNotFoundOrGenerated;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Scanner;

public class Moon {


    public static void preppify(){
        BloodMoon.BED_ENTER_FAIL_MESSAGE = "§dYou can not enter a bed during a blood-moon, sorry bestie...✨§d";
        BloodMoon.BLOODMOON_SUBTITLE = "§8The preppy moon slays once again ✨§8";
        BloodMoon.BLOODMOON_TITLE = "§dPreppy!§d";
        BloodMoon.BLOODMOON_CHATMESSAGE = "§d§d🎀Yars slay queen!!!✨§d";
    }







    final static String JSON_FILENAME = "data.json";


    //Takes in a percentage and returns the denomenator of the fraction
    public static double percentChance_to_fractChance(double percentage){
        return (1/(percentage/100));
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static ArmourData jsonInit(File JSON_FILE) {

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
                    return new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR);
                }


                ArmourData data = new Gson().fromJson(tempstr, ArmourData.class);
                //JsonType test = new JsonType(JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM, JsonData.NULLITEM);

                return data;








             //END OF IF STATEMENT BY THE WAY (Java is impossible to read)
            }

        }
        catch(NullPointerException e){
            BloodMoon.plugin.getLogger().severe("JsonFileNullError, plugin shutting down");
            return new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR);
        }

        catch(FileNotFoundOrGenerated e){
            return new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR);

        }

        catch(ExceptionInInitializerError | FileNotFoundException e){
            return new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR);

        }


        return new ArmourData(ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR, ArmourData.AIR);
    }



    public static void jsonSave(ArmourData toJson, File JSON_FILE){

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





}
