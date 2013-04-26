package Stack;
import java.util.Scanner;

public class Interface {
	 Scanner input = new Scanner (System.in);
		MyStack<Integer> stack = new MyStack<Integer>();
		public int menu () throws Exception{
			int choosed=0;
			System.out.println("Please choose the desired opearation");
			System.out.println("------------------------------------");
			System.out.println("To push press 1");
			System.out.println("To pop press 2");
			System.out.println("To exit press 3");
			 choosed=input.nextInt();
		return 	choosed;
		}
		public void operation(int choosed)throws Exception{
			Integer temp;
			if(choosed==1)
			{
			System.out.println("Please enter the integer");
			 temp=input.nextInt();
			stack.push(temp);
			}
			else
			{
				if(choosed==2)
				{
				temp=stack.pop();
				System.out.println("The integer poped is "+temp);
				}
				else
				{
					if(choosed==3)
					{
						System.exit(0);
					}
				}
			}
			
		}
		public static void main(String[] args) throws Exception
		{
			Interface test= new Interface();
			while(true)
			test.operation(test.menu());
		}

}


