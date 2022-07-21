# GitHub Source

## Overview
A Vance Connector which retrieves GitHub webhooks events, transform them into CloudEvents and deliver CloudEvents to the target URL.

## User Guidelines

## Connector Introduction
The GitHub Source is a [Vance Connector](https://github.com/linkall-labs/vance-docs/blob/main/docs/concept.md) designed to retrieves
GitHub webhooks events in various format, transform them into CloudEvents based on [CloudEvents Adapter specification](https://github.com
/cloudevents/spec/blob/main/cloudevents/adapters/github.md) and wrap the body of the original request into the data of CloudEvents.

The original GitHub webhooks events look like:
```
{
  "action": "created",
  "starred_at": "2022-07-21T06:28:23Z",
  "repository": {
    "id": 513353059,
    "node_id": "R_kgDOHpklYw",
    "name": "test-repo",
    "full_name": "XXXX/test-repo",
    "private": false,
    "owner": {
      "login": "XXXX",
      "type": "User",
      "site_admin": false
    },
    "html_url": "https://github.com/XXXX/test-repo",
    "description": null,
    "fork": false,
    "url": "https://api.github.com/repos/XXXX/test-repo",
    "forks_url": "https://api.github.com/repos/XXXX/test-repo/forks",
    "keys_url": "https://api.github.com/repos/XXXX/test-repo/keys{/key_id}",
    "collaborators_url": "https://api.github.com/repos/XXXX/test-repo/collaborators{/collaborator}",
    "teams_url": "https://api.github.com/repos/XXXX/test-repo/teams",
    "hooks_url": "https://api.github.com/repos/XXXX/test-repo/hooks",
    "issue_events_url": "https://api.github.com/repos/XXXX/test-repo/issues/events{/number}",
    "events_url": "https://api.github.com/repos/XXXX/test-repo/events",
    "assignees_url": "https://api.github.com/repos/XXXX/test-repo/assignees{/user}",
    "branches_url": "https://api.github.com/repos/XXXX/test-repo/branches{/branch}",
    "tags_url": "https://api.github.com/repos/XXXX/test-repo/tags",
    "blobs_url": "https://api.github.com/repos/XXXX/test-repo/git/blobs{/sha}",
    "git_tags_url": "https://api.github.com/repos/XXXX/test-repo/git/tags{/sha}",
    "git_refs_url": "https://api.github.com/repos/XXXX/test-repo/git/refs{/sha}",
    "trees_url": "https://api.github.com/repos/XXXX/test-repo/git/trees{/sha}",
    "statuses_url": "https://api.github.com/repos/XXXX/test-repo/statuses/{sha}",
    "languages_url": "https://api.github.com/repos/XXXX/test-repo/languages",
    "stargazers_url": "https://api.github.com/repos/XXXX/test-repo/stargazers",
    "contributors_url": "https://api.github.com/repos/XXXX/test-repo/contributors",
    "subscribers_url": "https://api.github.com/repos/XXXX/test-repo/subscribers",
    "subscription_url": "https://api.github.com/repos/XXXX/test-repo/subscription",
    "commits_url": "https://api.github.com/repos/XXXX/test-repo/commits{/sha}",
    "git_commits_url": "https://api.github.com/repos/XXXX/test-repo/git/commits{/sha}",
    "comments_url": "https://api.github.com/repos/XXXX/test-repo/comments{/number}",
    "issue_comment_url": "https://api.github.com/repos/XXXX/test-repo/issues/comments{/number}",
    "contents_url": "https://api.github.com/repos/XXXX/test-repo/contents/{+path}",
    "compare_url": "https://api.github.com/repos/XXXX/test-repo/compare/{base}...{head}",
    "merges_url": "https://api.github.com/repos/XXXX/test-repo/merges",
    "archive_url": "https://api.github.com/repos/XXXX/test-repo/{archive_format}{/ref}",
    "downloads_url": "https://api.github.com/repos/XXXX/test-repo/downloads",
    "issues_url": "https://api.github.com/repos/XXXX/test-repo/issues{/number}",
    "pulls_url": "https://api.github.com/repos/XXXX/test-repo/pulls{/number}",
    "milestones_url": "https://api.github.com/repos/XXXX/test-repo/milestones{/number}",
    "notifications_url": "https://api.github.com/repos/XXXX/test-repo/notifications{?since,all,participating}",
    "labels_url": "https://api.github.com/repos/XXXX/test-repo/labels{/name}",
    "releases_url": "https://api.github.com/repos/XXXX/test-repo/releases{/id}",
    "deployments_url": "https://api.github.com/repos/XXXX/test-repo/deployments",
    "created_at": "2022-07-13T02:21:06Z",
    "updated_at": "2022-07-21T06:28:23Z",
    "pushed_at": "2022-07-18T08:40:43Z",
    "git_url": "git://github.com/XXXX/test-repo.git",
    "ssh_url": "git@github.com:XXXX/test-repo.git",
    "clone_url": "https://github.com/XXXX/test-repo.git",
    "svn_url": "https://github.com/XXXX/test-repo",
    "homepage": null,
    "size": 2,
    "stargazers_count": 1,
    "watchers_count": 1,
    "language": null,
    "has_issues": true,
    "has_projects": true,
    "has_downloads": true,
    "has_wiki": true,
    "has_pages": false,
    "forks_count": 0,
    "mirror_url": null,
    "archived": false,
    "disabled": false,
    "open_issues_count": 2,
    "license": null,
    "allow_forking": true,
    "is_template": false,
    "web_commit_signoff_required": false,
    "topics": [

    ],
    "visibility": "public",
    "forks": 0,
    "open_issues": 2,
    "watchers": 1,
    "default_branch": "main"
  },
  "sender": {
    "login": "XXXX",
    "id": 75800782,
    "node_id": "MDQ6VXNlcjc1ODAwNzgy",
    "avatar_url": "https://avatars.githubusercontent.com/u/75800782?v=4",
    "gravatar_id": "",
    "url": "https://api.github.com/users/XXXX",
    "html_url": "https://github.com/XXXX",
    "followers_url": "https://api.github.com/users/XXXX/followers",
    "following_url": "https://api.github.com/users/XXXX/following{/other_user}",
    "gists_url": "https://api.github.com/users/XXXX/gists{/gist_id}",
    "starred_url": "https://api.github.com/users/XXXX/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.github.com/users/XXXX/subscriptions",
    "organizations_url": "https://api.github.com/users/XXXX/orgs",
    "repos_url": "https://api.github.com/users/XXXX/repos",
    "events_url": "https://api.github.com/users/XXXX/events{/privacy}",
    "received_events_url": "https://api.github.com/users/XXXX/received_events",
    "type": "User",
    "site_admin": false
  }
}
```
This GitHub star event will be transformed into a CloudEvents like:
```
CloudEvent:{
	id:'4ef226c0-08c7-11ed-998d-93772adf8abb', 
	source:https://api.github.com/repos/XXXX/test-repo, 
	type:'com.github.watch.started', 
	datacontenttype:'application/json', 
	time:2022-07-21T07:32:44.190Z, 
	data:JsonCloudEventData{
		http body
	}
}
```
## GitHub Source Configs
