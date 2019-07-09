package ora_procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UseTest {

    Scanner sc = new Scanner(System.in);

    public UseTest() throws Exception {
        menu();
    }

    public void menu() throws Exception {
        while (true) {
            System.out.print("메뉴: 입력=>1 검색 => 2 종료 => 3    ----  ");
            String menu = sc.nextLine();
            if (menu.equals("1")) {
                sawon_ins();
            } else if (menu.equals("2")) {
                listSearch();
            } else {
                System.exit(0);
            }
        }
    }

    public void listSearch() throws Exception {
        System.out.print("검색할 부서 번호를 입력:");
        String deptno = sc.nextLine();
        List<SawonVO> relist = Dao.getDao().sawonSearch(Integer.parseInt(deptno));
        StringBuffer sb = new StringBuffer();

        for (SawonVO e : relist) {
            sb.append(" 번호 :");
            sb.append(e.getSabun()).append("\n");
            sb.append(" 이름 :");
            sb.append(e.getSaname()).append("\n");
            sb.append(" 직업 :");
            sb.append(e.getSajob()).append("\n");
            sb.append(" 부서번호 : ");
            sb.append(e.getDeptno()).append("\n");
            sb.append(" 날짜 ");
            sb.append(e.getSahire()).append("\n");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        UseTest u = new UseTest();
    }

    private void sawon_ins() {
        SawonVO v = new SawonVO();
        System.out.print("사원이름 : ");
        v.setSaname(sc.nextLine());
        System.out.print("부서번호 : ");
        v.setDeptno(Integer.parseInt(sc.nextLine()));
        System.out.print("직급 : ");
        v.setSajob(sc.nextLine());
        System.out.print("급여 : ");
        v.setSapay(Integer.parseInt(sc.nextLine()));
        System.out.print("성별 : ");
        v.setSasex(sc.nextLine());
        System.out.print("매니저 : ");
        v.setSasex(sc.nextLine());
        
        Dao.getDao().insertDB(v);
    }
}
