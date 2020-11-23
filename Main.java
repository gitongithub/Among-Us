import java.util.*;
class Votes implements Comparator<Plist>{
	public int compare(Plist p1, Plist p2){
		return p2.getVotes()-p1.getVotes();
	}
}
class HealthPoint implements Comparator<Plist>{
	public int compare(Plist p1, Plist p2){
		return p1.getHP()-p2.getHP();
	}
}
abstract class Player{
	protected final int id;
	protected int HP;
	protected int votes_this_round;
	protected static ArrayList<Plist> players_alive=new ArrayList<>();
	public abstract boolean equals(Object o1);
	public Player(int id,int votes) {
		this.id=id;
		this.votes_this_round=votes;
	}
	public abstract void set_HP();
	public abstract void print_characters();
	public int voting() {
		int max=players_alive.size();
		Random rand = new Random();
		return rand.nextInt(max);
	}
	public int getHP() {
		return HP;
	}
	public void HP_inc(int x) {
		this.HP=x;
	}
	public int getVotes() {
		return votes_this_round;
	}
	public void setVotes(int votes_this_round) {
		this.votes_this_round = votes_this_round;
	}
	public int getId() {
		return id;
	}
	public static ArrayList<Plist> get_alive() {
		return players_alive;
	}
	public static void setPlayers_alive(ArrayList<Plist> id_players) {
		players_alive=id_players;		
	}
	
}
class Mafia extends Player{
	private static HashMap<Integer,Users<Integer,Mafia>> mafia_list=new HashMap<>();
	public Mafia(int id,int votes) {
		super(id,votes);
	}
	@Override
	public boolean equals(Object o1) {
		 if(o1 != null && getClass() == o1.getClass()) {
			 Mafia o = (Mafia) o1; 
			 return (id==o.id);
		 }
		 else {
			 return false;
		 }
	}
	public static int kill() {
		return 0;
	}
	@Override
	public void set_HP() {
		// TODO Auto-generated method stub
		this.HP=2500;
	}
	//@Override
	public static void my_knowns() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer,Users<Integer,Mafia>> entry : mafia_list.entrySet())  
			System.out.print("Player" + entry.getKey()+",");
		System.out.println(" were Mafias");	}
	public static void setMafia_list(int id,Users<Integer,Mafia> mafia) {
		Mafia.mafia_list.put(id, mafia);
	}
	public static int kill_random() {
		int max=players_alive.size();
		Random rand = new Random();
		return players_alive.get(rand.nextInt(max)).getId();
	}
	@Override
	public void print_characters() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer,Users<Integer,Mafia>> entry : mafia_list.entrySet())  
			System.out.print("Player" + entry.getKey()+",");
	}
}
class Detective extends Player{
	private static HashMap<Integer,Users<Integer,Detective>> detective_list=new HashMap<>();
	public Detective(int id,int votes) {
		super(id,votes);
	}
	@Override
	public boolean equals(Object o1) {
		 if(o1 != null && getClass() == o1.getClass()) {
			 Detective o = (Detective) o1; 
			 return (id==o.id);
		 }
		 else {
			 return false;
		 }
	}
	public static int detect_random() {
		int max=players_alive.size();
		Random rand = new Random();
		return players_alive.get(rand.nextInt(max)).getId();
	}
	@Override
	public void set_HP() {
		// TODO Auto-generated method stub
		this.HP=800;
	}
	//@Override
	public static void my_knowns() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer,Users<Integer,Detective>> entry : detective_list.entrySet())  
			System.out.print("Player" + entry.getKey()+",");
		System.out.println(" were Detectives"); 
	}
	public static void setDetective_list(int id,Users<Integer,Detective> detective) {
		Detective.detective_list.put(id, detective);
	}
	@Override
	public void print_characters() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer,Users<Integer,Detective>> entry : detective_list.entrySet())  
			System.out.print("Player" + entry.getKey()+",");
	}
}
class Healer extends Player{
	public static HashMap<Integer,Users<Integer,Healer>> healer_list=new HashMap<>();
	public Healer(int id,int votes) {
		super(id,votes);
	}
	@Override
	public boolean equals(Object o1) {
		 if(o1 != null && getClass() == o1.getClass()) {
			 Healer o = (Healer) o1; 
			 return (id==o.id);
		 }
		 else {
			 return false;
		 }
	}
	@Override
	public void set_HP() {
		// TODO Auto-generated method stub
		this.HP=800;
	}
	///@Override
	public static void my_knowns() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer,Users<Integer,Healer>> entry : healer_list.entrySet())  
			System.out.print("Player" + entry.getKey()+",");
		System.out.println(" were Healers");	}
	public static void setHealer_list(int id,Users<Integer,Healer> healer) {
		Healer.healer_list.put(id, healer);
	}
	@Override
	public void print_characters() {
		// TODO Auto-generated method stub
		for (Map.Entry<Integer,Users<Integer,Healer>> entry : healer_list.entrySet())  
			System.out.print("Player" + entry.getKey()+",");
	}
	public static int heal() {
		int max=players_alive.size();
		Random rand = new Random();
		return players_alive.get(rand.nextInt(max)).getId();
	}
}
class Commoner extends Player{
	public Commoner(int id,int votes) {
		super(id,votes);
	}
	@Override
	public boolean equals(Object o1) {
		 if(o1 != null && getClass() == o1.getClass()) {
			 Commoner o = (Commoner) o1; 
			 return (id==o.id);
		 }
		 else {
			 return false;
		 }
	}
	@Override
	public void set_HP() {
		// TODO Auto-generated method stub
		this.HP=1000;
	}

	//@Override
	public void my_knowns() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void print_characters() {
		// TODO Auto-generated method stub
		
	}
}
class Plist{
	private final int id;
	private int HP;
	private int votes;
	public Plist(int id,int HP,int votes) {
		this.id=id;
		this.HP=HP;
		this.votes=votes;
	}
	public int getId() {
		return id;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
}
class Game{
	public Scanner inp =new Scanner(System.in);
	private  int n; 
	private int mafia_count;
	private int detective_count;
	private int healer_count;
	private int commoner_count;
	private final int n_mafia;
	private final int n_detective;
	private final int n_healer;
	private final int n_commoner;
	private ArrayList<Integer> id_alive;
	private HashMap<Integer,Users<Integer,Player>> player_list=new HashMap<>();
	private HashMap<Integer,Users<Integer,Commoner>> commoner_list=new HashMap<>();
	public Game(int n,int ch) {
		player_list=new HashMap<Integer,Users<Integer,Player>>();
		this.n = n;
		this.n_mafia=n/5;
		this.mafia_count=n/5;
		this.n_detective=n/5;
		this.detective_count=n/5;
		this.healer_count=Math.max(1,n/10);
		this.n_healer=this.healer_count;
		this.commoner_count= (n-this.mafia_count-this.detective_count-this.healer_count);
		this.n_commoner=this.commoner_count;
		id_alive =new ArrayList<Integer>();
		for(int i=1;i<=n;i++) {
			id_alive.add(i);
		}
		assign_characters(ch);
	}
	public void assign_characters(int ch) {
		ArrayList<Integer> all_ids=new ArrayList<Integer>();		
		for(int i=2;i<=this.n;i++) {
			all_ids.add(i);
		}
		int i=0;
		if(ch==1) {
			Player temp =new Mafia(1,0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Mafia> m_user=new Users<Integer,Mafia>(1,(Mafia) temp);
			setPlayer_list(1,user);
			Mafia.setMafia_list(1,m_user);
			Player.players_alive.add(new Plist(1,2500,0));
			i=1;
		}
		for(;i<this.n_mafia;i++) {
			Random rand = new Random();
			int x=rand.nextInt(all_ids.size());
			Player temp =new Mafia(all_ids.get(x),0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Mafia> m_user=new Users<Integer,Mafia>(1,(Mafia) temp);
			setPlayer_list(all_ids.get(x),user);
			Mafia.setMafia_list(all_ids.get(x),m_user);
			Player.players_alive.add(new Plist(all_ids.get(x),2500,0));
			all_ids.remove(x);
			
		}
		i=0;
		if(ch==2) {
			Player temp =new Detective(1,0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Detective> m_user=new Users<Integer,Detective>(1,(Detective) temp);
			setPlayer_list(1,user);
			Detective.setDetective_list(1,m_user);
			Player.players_alive.add(new Plist(1,800,0));
			i=1;
		}
		for(;i<this.n_detective;i++) {
			Random rand = new Random();
			int x=rand.nextInt(all_ids.size());
			Player temp =new Detective(all_ids.get(x),0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Detective> m_user=new Users<Integer,Detective>(1,(Detective) temp);
			setPlayer_list(all_ids.get(x),user);
			Detective.setDetective_list(all_ids.get(x),m_user);
			Player.players_alive.add(new Plist(all_ids.get(x),800,0));
			all_ids.remove(x);
		}
		i=0;
		if(ch==3) {
			Player temp =new Healer(1,0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Healer> m_user=new Users<Integer,Healer>(1,(Healer) temp);
			setPlayer_list(1,user);
			Healer.setHealer_list(1,m_user);
			Player.players_alive.add(new Plist(1,800,0));
			i=1;
		}
		for(;i<this.n_healer;i++) {
			Random rand = new Random();
			int x=rand.nextInt(all_ids.size());
			Player temp =new Healer(all_ids.get(x),0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Healer> m_user=new Users<Integer,Healer>(1,(Healer) temp);
			setPlayer_list(all_ids.get(x),user);
			Healer.setHealer_list(all_ids.get(x),m_user);
			Player.players_alive.add(new Plist(all_ids.get(x),800,0));
			all_ids.remove(x);
		}
		i=0;
		if(ch==4) {
			Player temp =new Commoner(1,0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Commoner> m_user=new Users<Integer,Commoner>(1,(Commoner) temp);
			setPlayer_list(1,user);
			setCommoner_list(1,m_user);
			Player.players_alive.add(new Plist(1,1000,0));
			i=1;
		}
		for(;i<this.n_commoner;i++) {
			Random rand = new Random();
			int x=rand.nextInt(all_ids.size());
			Player temp =new Commoner(all_ids.get(x),0);
			temp.set_HP();
			Users<Integer,Player> user=new Users<Integer,Player>(1,temp);
			Users<Integer,Commoner> m_user=new Users<Integer,Commoner>(1,(Commoner) temp);
			setPlayer_list(all_ids.get(x),user);
			setCommoner_list(all_ids.get(x),m_user);
			Player.players_alive.add(new Plist(all_ids.get(x),1000,0));
			all_ids.remove(x);
		}
		if(ch==1) {
			System.out.print("You are a Mafia.");
			System.out.println("Other Mafias are:");
		}
		else if(ch==2) {
			System.out.print("You are a Detective.");
			System.out.println("Other Detectives are:");
		}
		else if(ch==3) {
			System.out.print("You are a Healer.");
			System.out.println("Other Healers are:");
		}
		else {
			System.out.println("You are a Commoner");
		}
	}
	public void show_details() {
		Mafia.my_knowns();
		Detective.my_knowns();
		Healer.my_knowns();
		for (Map.Entry<Integer,Users<Integer,Commoner>> entry : commoner_list.entrySet())  
            System.out.print("Player" + entry.getKey()+",");
		System.out.println(" were Commoners");
	}
	public void others() {
		for (Map.Entry<Integer,Users<Integer,Player>> entry : player_list.entrySet())  
		{
			if(entry.getValue().getType() instanceof Mafia && player_list.get(1).getType() instanceof Mafia) {
				if(!player_list.get(1).getType().equals(entry.getValue().getType())) {
					System.out.println("Player"+entry.getKey());
				}
				
			}
			else if(entry.getValue().getType() instanceof Detective && player_list.get(1).getType() instanceof Detective) {
				if(!player_list.get(1).getType().equals(entry.getValue().getType())) {
					System.out.println("Player"+entry.getKey());
				}
				
			}
			else if(entry.getValue().getType() instanceof Healer && player_list.get(1).getType() instanceof Healer) {
				if(!player_list.get(1).getType().equals(entry.getValue().getType())) {
					System.out.println("Player"+entry.getKey());
				}
				
			}
		}
	}
	public void play_game(){
		int k=1;
		do {
			System.out.println("Round "+k+":");
			System.out.print(Player.players_alive.size()+" players are remaining:");
			for(int i=0;i<Player.players_alive.size();i++) {
				System.out.print("Player"+Player.players_alive.get(i).getId()+", ");
			}
			System.out.println();
			int which;
			do{
				which=Mafia.kill_random();		
			}while(player_list.get(which).getType() instanceof Mafia);
			if(player_list.get(1).getType() instanceof Mafia && player_list.get(1).getStatus()==1)
			{
				int t;
				do{
					System.out.println("Choose a target to kill (which isn't mafia):");
					t=inp.nextInt();
				}while(t>n || player_list.get(t).getType() instanceof Mafia || player_list.get(t).getStatus()==0);
				which=t;
			}
			else {
				System.out.println("Mafias have chosen their target");
			}

			Collections.sort(Player.players_alive,new HealthPoint());
			int hp_div=player_list.get(which).getType().getHP();
			int ctr=0;
			for(int i=0;i<Player.players_alive.size();i++) {
				if(player_list.get(Player.players_alive.get(i).getId()).getType() instanceof Mafia) {
					
					int temp=hp_div/(getmafia_count()-ctr);
					//System.out.println(hp_div);

					hp_div=hp_div-temp;
					if(temp>player_list.get(Player.players_alive.get(i).getId()).getType().getHP()) {
						hp_div=hp_div+temp;
						hp_div-=player_list.get(Player.players_alive.get(i).getId()).getType().getHP();
						temp=player_list.get(Player.players_alive.get(i).getId()).getType().getHP();
					}
					//System.out.println(temp);
					player_list.get(Player.players_alive.get(i).getId()).getType().HP_inc(player_list.get(Player.players_alive.get(i).getId()).getType().getHP()-temp);
					Player.players_alive.get(i).setHP(Player.players_alive.get(i).getHP()-temp);
					ctr++;
				}
			}
			player_list.get(which).getType().HP_inc(0);
			for(int i=0;i<Player.players_alive.size();i++) {
				if(Player.players_alive.get(i).getId()==which) {
					Player.players_alive.get(i).setHP(0);
				}
			}
			int fvote=0;
			int detect=-1;
			if(getdetective_count()>0) {
				
				do{
					detect=Detective.detect_random();		
				}while(player_list.get(detect).getType() instanceof Detective);
				if(player_list.get(1).getType() instanceof Detective && player_list.get(1).getStatus()==1)
				{
					int t;
					do{
						System.out.println("Choose a target to detect (which isn't a detective):");
						t=inp.nextInt();
					}while(t>n || player_list.get(t).getType() instanceof Detective || player_list.get(t).getStatus()==0);
					detect=t;
				}
				if(player_list.get(detect).getType() instanceof Mafia) {
					fvote=1;
					setmafia_count(getmafia_count()-1);
					player_list.get(detect).setStatus(0);
					for(int i=0;i<Player.players_alive.size();i++) {
						if(Player.players_alive.get(i).getId()==detect) {
							Player.players_alive.remove(i);
						}
					}
					if(getmafia_count()==0) {
						System.out.println("Player"+player_list.get(detect).getType().getId()+" was Mafia");
						System.out.println("The Mafias have Lost");
						return;
					}
					System.out.println("Player"+player_list.get(detect).getType().getId()+" was Mafia");
				}

			}
			System.out.println("Detective have chosen their target");
			if(gethealer_count()>0) {
				int hp_boost=Healer.heal();
				if(player_list.get(1).getType() instanceof Healer && player_list.get(1).getStatus()==1)
				{
					int t;
					do{
						System.out.println("Choose a valid player to heal:");
						t=inp.nextInt();
						hp_boost=t;
					}while(t>n || player_list.get(t).getStatus()==0);
				}
				
			
			for(int i=0;i<Player.players_alive.size();i++) {
				if(Player.players_alive.get(i).getId()==hp_boost) {
					Player.players_alive.get(i).setHP(Player.players_alive.get(i).getHP()+500);
					player_list.get(Player.players_alive.get(i).getId()).getType().HP_inc(500+player_list.get(Player.players_alive.get(i).getId()).getType().getHP());
				}
			}
			}
			System.out.println("Healers have healed");
			int fnot=0;
			
			
//			for(int i=0;i<Player.players_alive.size();i++) {
//
//					System.out.println(Player.players_alive.get(i).getId()+"    " +Player.players_alive.get(i).getHP()); 
//
//			}
			
			
			for(int i=0;i<Player.players_alive.size();i++) {
				if(player_list.get(Player.players_alive.get(i).getId()).getType() instanceof Mafia) {
					
				}
				else {
					if(Player.players_alive.get(i).getHP()==0) {
						fnot=1;
					}
				}
			}
			if(fnot==1) {
				if(player_list.get(which).getType() instanceof Commoner) {
					setcommoner_count(getcommoner_count()-1);
				}
				else if(player_list.get(which).getType() instanceof Detective) {
					setdetective_count(getdetective_count()-1);	
				}
				else {
					sethealer_count(gethealer_count()-1);
				}
				System.out.println("--End of Actions--");
				System.out.println("Player"+which+" has died.");
				//n--;
				player_list.get(which).setStatus(0);
				for(int i=0;i<Player.players_alive.size();i++) {
					if(Player.players_alive.get(i).getId()==which) {
						Player.players_alive.remove(i);
					}
				}
			}
			else {
				System.out.println("--End of Actions--");
				System.out.println("No one died");
			}
			if(getmafia_count()>=getdetective_count()+getcommoner_count()+gethealer_count())
				break;
			if(fvote==0) {
			do {
				for (int i=0;i<Player.players_alive.size();i++) {
					Player.players_alive.get(i).setVotes(0);    
				}
				for (int i=0;i<Player.players_alive.size();i++) {
					int x;
					//System.out.println("")
					if(player_list.get(Player.players_alive.get(i).getId()).getStatus()==1)
					{
						if(Player.players_alive.get(i).getId()==1) {
							do {
								System.out.println("Choose a player to vote out:");
								x=inp.nextInt();
							}while(x>n || player_list.get(x).getStatus()==0);
							for(int j=0;j<Player.players_alive.size();j++) {
								if(Player.players_alive.get(j).getId()==x) {
									Player.players_alive.get(j).setVotes(Player.players_alive.get(j).getVotes()+1);;
								}
							}
						}
						else {
							x=player_list.get(Player.players_alive.get(i).getId()).getType().voting();
							Player.players_alive.get(x).setVotes(Player.players_alive.get(x).getVotes()+1);
						}
					}
				}
				Collections.sort(Player.players_alive,new Votes());
			}while(Player.players_alive.get(0).getVotes()!=Player.players_alive.get(1).getVotes());
			
			System.out.println("Player"+Player.players_alive.get(0).getId()+" voted out");
			if(player_list.get(Player.players_alive.get(0).getId()).getType() instanceof Commoner) {
				setcommoner_count(getcommoner_count()-1);
			}
			else if(player_list.get(Player.players_alive.get(0).getId()).getType() instanceof Detective) {
				setdetective_count(getdetective_count()-1);	
			}
			else if(player_list.get(Player.players_alive.get(0).getId()).getType() instanceof Mafia) {
				setmafia_count(getmafia_count()-1);	
			}
			else {
				sethealer_count(gethealer_count()-1);
			}
			player_list.get(Player.players_alive.get(0).getId()).setStatus(0);
			Player.players_alive.remove(0);
			}
			else {
				System.out.println("Player"+player_list.get(detect).getType().getId()+" has been voted out");
			}
			System.out.println("--Round "+k+" over--");
			if(getmafia_count()==0) {
				System.out.println("The Mafias have Lost");
				return;
			}
			for (int i=0;i<Player.players_alive.size();i++) {
				Player.players_alive.get(i).setVotes(0);    
			}
			
			
			k++;
		}while(getmafia_count()<getdetective_count()+getcommoner_count()+gethealer_count());
		System.out.println("The Mafias have Won");
	}
	public void setPlayer_list(int id,Users<Integer,Player> player_x) {
		this.player_list.put(id, player_x);
	}
	public void setCommoner_list(int id,Users<Integer,Commoner> player_x) {
		this.commoner_list.put(id, player_x);
	}
	public int getdetective_count() {
		return this.detective_count;
	}
	public int gethealer_count() {
		return this.healer_count;
	}
	public int getmafia_count() {
		return this.mafia_count;
	}
	public int getcommoner_count() {
		return this.commoner_count;
	}
	public void setmafia_count(int c) {
		this.mafia_count=c;
	}
	public void sethealer_count(int c) {
		this.healer_count=c;
	}
	public void setdetective_count(int c) {
		this.detective_count=c;
	}
	public void setcommoner_count(int c) {
		this.commoner_count=c;
	}
	
}
class Users <Integer,T>{
	private int alive;
	private T char_type;
	public Users(int alive, T char_type) {
		this.alive=alive;
		this.char_type=char_type;
	}
	public void setStatus(int i) {
		this.alive=i;
	}
	public int getStatus() {
		return alive;
	}
	public T getType() {
		return char_type;
	}
}
public class Main {
	static Scanner inp =new Scanner(System.in);
	public static void main(String args[]) {
		System.out.println("Welcome to Mafia");
		int n;
		do {
			System.out.print("Enter Number of Players:");
			n=inp.nextInt();
			if(n<6)
				System.out.println("Atleast 6 players should play");
		}while(n<6);
		int ch;
		do {
			System.out.println("Choose a Character\n1)Mafia\n2)Detective\n3)Healer\n4)Commoner\n5)Assign Randomly");
			ch=inp.nextInt();
			if(ch>5 || ch<1)
				System.out.println("Invalid choice");
		}while(ch>5 || ch<1);
		System.out.println("You are Player1");
		if(ch==5) {
			 Random rand = new Random();
			 ch=1+rand.nextInt(4);
		}
		Game gameplay =new Game(n,ch);
		gameplay.others();
		gameplay.play_game();
		gameplay.show_details();
		
	}
}
