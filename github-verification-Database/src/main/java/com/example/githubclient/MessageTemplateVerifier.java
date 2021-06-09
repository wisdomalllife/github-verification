package com.example.githubclient;

import com.example.githubclient.model.CommitNode;
import com.example.githubclient.model.IssueComment;
import com.example.githubclient.model.ReviewComment;
import com.example.githubclient.service.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class MessageTemplateVerifier {

    @Autowired
    private GitHubClient gitHubClient;

    private static final String VERIFICATION_RESULT = "Auto-Verifier finding";
    private static final String regex = "^(GENERATOR|LEETCODE)\\s(1021|1022|1013|2021|2022)\\s(Added|Deleted|Refactored|Moved|Fixed).+";
    private static final String OK_MESSAGE = "Nice Job! Keep Going";
    private static final String RAISE_MESSAGE =
            "Check result:\n" +
            "\n" +
            "    Commit title must start with prefix in ['GENERATOR', 'LEETCODE']\n" +
            "    Commit title must contain group number in ['1021', '1022', '1013', '2021', '2022']\n" +
            "    Commit title action must start with ['Added', 'Deleted', 'Refactored', 'Moved', 'Fixed']\n" +
            "\n";

    // private static final int MANDATORY_CALL_TIME = 1209600;  // 2 weeks

    public static boolean process(String title, String regex) {
        return title.matches(regex);
    }

    public static boolean process(String title) {
        return process(title, regex);
    }

    private static String commitNodesChecker(List<CommitNode> commitNodeList, String okMessage) {

        boolean isOK = true;

        StringBuilder message = new StringBuilder(VERIFICATION_RESULT + "\n" + "\n");

        for (CommitNode node : commitNodeList) {
            String title = node.getCommit().getMessage();
            boolean isValidTitle = process(title);

            if (!isOK) {
                message.append("  - ").append(title).append("\n");
            } else if (!isValidTitle) {
                isOK = false;
                message.append("Your Commit titles: ").append("\n");
            }
        }

        if (isOK) {
            message.append("\n").append(okMessage);
        } else {
            message.append("\n").append(MessageTemplateVerifier.RAISE_MESSAGE);
        }

        if (okMessage.isEmpty()) {
            return okMessage;
        }
        return message.toString();
    }


    public static String buildMessage(List<IssueComment> issueCommentList, List<ReviewComment> reviewCommentList, List<CommitNode> commitNodeList) {

         List<IssueComment> issueCommentListVerifications = issueCommentList.stream()
                .filter(x -> x.getBody().startsWith(VERIFICATION_RESULT))
                .sorted(Comparator.comparing(IssueComment::getCreationDate))
                .collect(Collectors.toList());

        List<ReviewComment> reviewCommentListVerifications = reviewCommentList.stream()
                .filter(x -> x.getBody().startsWith(VERIFICATION_RESULT))
                .sorted(Comparator.comparing(ReviewComment::getCreationDate))
                .collect(Collectors.toList());

        if (issueCommentListVerifications.isEmpty() && reviewCommentListVerifications.isEmpty()) {
            //first message
            return commitNodesChecker(commitNodeList, OK_MESSAGE);
        }

        boolean review;
        boolean issue;

        if (issueCommentList.isEmpty() && reviewCommentList.isEmpty()) {
            return "";
        }

        if (issueCommentList.isEmpty()) {
            review = reviewCommentList.get(reviewCommentList.size() - 1).getBody().startsWith(VERIFICATION_RESULT);
            if (!review) {
                return commitNodesChecker(commitNodeList, "");
            }
            return "";
        }

        if (reviewCommentList.isEmpty()) {
            issue = issueCommentList.get(issueCommentList.size() - 1).getBody().startsWith(VERIFICATION_RESULT);
            if (!issue) {
                return commitNodesChecker(commitNodeList, "");
            }
            return "";
        }

        review = reviewCommentList.get(reviewCommentList.size() - 1).getBody().startsWith(VERIFICATION_RESULT);
        issue = issueCommentList.get(issueCommentList.size() - 1).getBody().startsWith(VERIFICATION_RESULT);

        if (!review || !issue) {
            return commitNodesChecker(commitNodeList, "");
        }

        return "";
    }

}
