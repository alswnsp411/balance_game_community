package com.example.balance_game_community.fileIO;

import com.example.balance_game_community.AppConfig;
import com.example.balance_game_community.TestDataSource;
import com.example.balance_game_community.balanceGame.BalanceGameDAO;
import com.example.balance_game_community.balanceGameComment.BalanceGameCommentDAO;
import com.example.balance_game_community.balanceGameVote.BalanceGameVoteDAO;
import com.example.balance_game_community.balanceGameVote.Difficulty;
import com.example.balance_game_community.balanceGameVote.Preference;
import com.example.balance_game_community.member.MemberDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/voteBalanceGameDifficultyServlet")
public class voteBalanceGameDifficultyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // TODO 로그인 기능 구현 후 : TestDataSource -> DataSource
        AppConfig testAppConfig = new AppConfig(new TestDataSource());
        MemberDAO memberDAO = testAppConfig.getMemberDAO();
        BalanceGameVoteDAO balanceGameVoteDAO = testAppConfig.getBalanceGameVoteDAO();
        BalanceGameDAO balanceGameDAO = testAppConfig.getBalanceGameDAO();
        BalanceGameCommentDAO balanceGameCommentDAO = testAppConfig.getBalanceGameCommentDAO();

        // TODO 로그인 기능 구현 후 : 로그인 된 멤버 id 사용
        Long memberId = 10L;

        Long balanceGameId = Long.parseLong(request.getParameter("balanceGameId"));

        Difficulty difficulty = Difficulty.valueOf(request.getParameter("difficulty"));

        balanceGameVoteDAO.voteDifficulty(memberId, balanceGameId, difficulty);

        System.out.println("difficulty = " + difficulty.name());
        System.out.println("memberId = " + memberId);
        System.out.println("balanceGameId = " + balanceGameId);

        response.sendRedirect(request.getHeader("referer"));
    }
}