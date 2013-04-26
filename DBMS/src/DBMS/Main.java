package DBMS;

public class Main {
	public static void main(String[] args) throws Exception {
	//	Schema schema = new Schema();
		//test create
		Create c = new Create("test", new String[] { "ID", "Name", "age",
				"City" });
		//String [][] cc=new String[][]{{"ID","integer"},{"Name","varchar_5"},{"age","integer"},{"City","varchar_6"}};
		//tableData data = new tableData("test",cc);
	//	schema.add(data);
	
		c.execute();
		//--------------------------------------------------------------------------
		Load load = new Load();
	//	load.execute("test",schema);
		Table t = load.getTable();
		System.out.println(t.toString());
		//----------------------------------------------------------------------------
		Insert insert = new Insert();
		insert.excute(t, new String [] {"ID","City","age"}, new String[]{"6","hazem","20"});
		insert.excute(t, new String [] {"ID","City","age"}, new String[]{"6","hazem","20"});
		insert.excute(t, new String [] {"ID","City","age"}, new String[]{"6","hazem","20"});
		insert.excute(t, new String[]{"age"}, new String[]{"1"});
		System.out.println(t.toString());	
		//--------------------------------------------------------------------------------
		Save save = new Save();
		save.execute(t);
	//	load.execute("test",schema);
		t=load.getTable();
		System.out.println(t.toString());
		//----------------------------------------------------------------------------------
		Update update =new Update();
		update.execute(t, new String []{"ID"},new String[]{"2"},new int[]{1,2});
		update.execute(t, new String[]{"City","ID","Name","age"}, new String[]{"alex","2","hazem","5"}, null);
		System.out.println(t.toString());
		save.execute(t);
		//----------------------------------------------------------------------------------
		/*Delete delete=new Delete();
		delete.excute(t, new int[]{1});
		System.out.println(t.toString());
		*///-----------------------------------------------------------------------------------
		/*Select select =new Select();
		select.execute(t, new String[]{"City","age","ID"}, new int[]{2,3});
		System.out.println(select.getRequired().toString());
		
		select.execute(t, null, new int[]{2,3});
		System.out.println(select.getRequired().toString());
		
		select.execute(t, new String[]{"City","age","ID"}, null);
		System.out.println(select.getRequired().toString());
		select.execute(t, null, null);
		System.out.println(select.getRequired().toString());
		*///----------------------------------------------------------
		//save.execute("test");
		
	}

}
