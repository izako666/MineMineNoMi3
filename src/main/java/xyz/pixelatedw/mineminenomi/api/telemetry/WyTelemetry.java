package xyz.pixelatedw.mineminenomi.api.telemetry;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;

public class WyTelemetry
{

	private static String urlConnection;
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	private static Gson gson = new Gson();
	
	private static StatDataCompound structuresDataCompound = new StatDataCompound();
	private static StatDataCompound killsDataCompound = new StatDataCompound();
	private static StatDataCompound abilitiesDataCompound = new StatDataCompound();
	private static StatDataCompound miscDataCompound = new StatDataCompound();
	private static StatDataCompound devilFruitsDataCompound = new StatDataCompound();
	
	static
	{
		if (WyDebug.isDebug())
			urlConnection = "http://localhost/mmnm-webserver/api";
		else
			urlConnection = "http://pixelatedw.xyz/api";
	}

	public static void addStructureStat(String id, String name, int value)
	{
		structuresDataCompound.put(id, name, value);
		//debugJSON(structuresDataCompound);
	}
	
	public static void addKillStat(String id, String name, int value)
	{
		killsDataCompound.put(id, name, value);
		//debugJSON(killsDataCompound);
	}
	
	public static void addAbilityStat(String id, String name, int value)
	{
		abilitiesDataCompound.put(id, name, value);
		//debugJSON(abilitiesDataCompound);
	}
	
	public static void addMiscStat(String id, String name, int value)
	{
		miscDataCompound.put(id, name, value);
		//debugJSON(miscDataCompound);
	}
	
	public static void addDevilFruitStat(String id, String name, int value)
	{
		devilFruitsDataCompound.put(id, name, value);
		//debugJSON(devilFruitsDataCompound);
	}
	
	public static void sendAllData()
	{
		Thread httpThread = new Thread()
		{
			@Override
			public void run()
			{
				try
				{		
					Object[][] paths = new Object[][] 
					{
						{"/addStructureStat", structuresDataCompound},
						{"/addKillStat", killsDataCompound},
						{"/addAbilityStat", abilitiesDataCompound},
						{"/addMiscStat", miscDataCompound},
						{"/addDFStat", devilFruitsDataCompound}
					};
							
					for(Object[] o : paths)
					{
						String url = (String) o[0];
						StatDataCompound compound = (StatDataCompound) o[1];
						
						if(compound.data.isEmpty())
							continue;
						
						String json = gson.toJson(compound);
						HttpPost post = new HttpPost(urlConnection + "" + url);
						StringEntity postingString;
						postingString = new StringEntity(json);
						String size = WyHelper.formatBytes(json.getBytes().length);
						debugJSON(compound);
						post.setEntity(postingString);
						post.setHeader("Content-Type", "application/json");
						HttpResponse response = httpClient.execute(post);
						ResponseHandler<String> handler = new BasicResponseHandler();
						String body = handler.handleResponse(response);
						System.out.println(body.isEmpty() ? "Success" : body);
						
						compound.empty();
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}			
			}
		};
		httpThread.setName("Mine Mine no Mi - Stats POST");
		httpThread.start();
	}

	private static void debugJSON(StatDataCompound compound)
	{
		String json = gson.toJson(compound);
		String size = WyHelper.formatBytes(json.getBytes().length);
		WyDebug.debug("\n JSON: " + json + "\n Size: " + size);
	}
	
	private static class StatDataCompound
	{
		private String mcVersion;
		private String modVersion;
		private int source;
		private HashMap<String, StatData> data = new HashMap<String, StatData>();
		
		public StatDataCompound()
		{
			this.mcVersion = ID.PROJECT_MCVERSION;
			this.modVersion = ID.PROJECT_VERSION;
			this.source = 0;
		}
		
		public void put(String id, String name, int value)
		{
			if(data.containsKey(id))
				data.get(id).value += value;
			else
			{
				StatData newData = new StatData(name, value);
				data.put(id, newData);
			}
		}
		
		public void empty()
		{
			data = new HashMap<String, StatData>(); 
		}
	}
	
	private static class StatData
	{
		private String name;
		private int value;
		
		public StatData(String name, int value)
		{
			this.name = name;
			this.value = value;
		}
	}
}