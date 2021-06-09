package com.example.githubclient;

import com.example.githubclient.model.CommitNode;
import com.example.githubclient.model.IssueComment;
import com.example.githubclient.model.Pull;
import com.example.githubclient.model.ReviewComment;
import com.example.githubclient.service.GitHubClient;
import com.example.githubclient.service.GitHubService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

public class GitHubServiceTest extends AbstractGithubClientTest {

    String owner = "TEST OWNER";
    String repo = "TEST REPO";
    int number = 65536;

    GitHubService service;
    GitHubClient mock;

    @Before
    public void init() {
        mock = Mockito.mock(GitHubClient.class, RETURNS_DEEP_STUBS);
        service = new GitHubService(mock);
    }

    @Test
    public void testGetPulls() throws IOException {
        Pull pull = new Pull();
        pull.setTitle("Test Pull");
        when(mock.getUserRepoPulls(owner, repo)).thenReturn(Collections.singletonList(pull));
        List<Pull> pulls = service.getPulls(owner, repo);
        assertEquals("Hello Test Pull", pulls.get(0).getTitle());
    }

    @Test
    public void testGetCommits() throws IOException {
        String testString = "HFOIJGWGWGWGWEGO";
        CommitNode node = new CommitNode();
        node.setSha(testString);
        when(mock.getCommitNodes(owner, repo, number)).thenReturn(Collections.singletonList(node));
        List<CommitNode> commitNodes = service.getCommitNode(owner, repo, number);
        assertEquals(new StringBuilder(testString).reverse().toString(), commitNodes.get(0).getSha());
    }

    @Test
    public void testGetIssue() throws IOException {
        IssueComment comment = new IssueComment();
        comment.setBody("BODY BODY BODY");
        when(mock.getPullIssue(owner, repo, number)).thenReturn(Collections.singletonList(comment));
        List<IssueComment> issues = service.getIssues(owner, repo, number);
        assertEquals("NICE BODY BODY BODY", issues.get(0).getBody());
    }

    @Test
    public void testGetReview() throws IOException {
        ReviewComment comment = new ReviewComment();
        comment.setBody("BODY BODY BODY");
        when(mock.getPullReview(owner, repo, number)).thenReturn(Collections.singletonList(comment));
        List<ReviewComment> reviews = service.getReviews(owner, repo, number);
        assertEquals("NICE BODY BODY BODY", reviews.get(0).getBody());
    }



}
