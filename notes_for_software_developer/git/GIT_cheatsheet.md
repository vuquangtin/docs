GIT cheatsheet 
==============================

` git clone ` download & create remote

` git branch -d {name} ` 

` git merge ` : strategiess recursive | fast forward

```
   <<<<<<<<<<<<< HEAD 
	This contains my local version  !!!!
   =======================
	This is from server
   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 ```

 ` git push {remote} {branch}`

 ` git push -u origin {branch-name}`

 ` git branch -r ` remote branches

 ` git remote show {remote} `

 ` git push: {branch}` deletes remote branch

 ` git branch -D {branch}` force to delete locally

 ` git remote prune origin ` to clean up delete remoted branches

 ` git push {remote}  {localBranch} : {serverBranch}`

 GIT TAG
 --------------------

 `git tag`

 `git tag -a v0.0.0.3 -m 'Version 0.0.0.3'`

 ` git push --tags`

 ` git checkout {tag}`

 Types of tags

 - Lightweight : no message no header

 - Signed

 - Annotaded `-a {version} -m {message}`


 GIT REBASE 
 --------------------

- Move all changes to temp
- Run all commits
- Move all commits in the temp one at a time

 `git rebase`

Options

`git rebase --continue`

`git rebase --skip`

`git rebase --abort`

`git rebase -i HEAD~3`

In Popup
----

`.pick`

`.reword`

`.edit`

`.squash`

GIT LOG
------------------

`git log --pretty=oneline`

`git log --oneline -p`

`git log --oneline -graph`

`git blame`

`git rm --cached`


GIT CONFIG
------------------

`git config --global color.ui true`

`git config --global merge.tool {tool}`

`git config user.email {myMail}`

`git config --global alias.{myAlias}  log --`

`git config --global  core.autocrlf {input-> for Linux}`




GIT STASH
---------------

`git stash save`

`git stash apply`

`git stash list`

`git stash drop`

`git stash save --keep-index ` : causes the staging area not to be stashed

`git stash save --included-untracked ` : causes the untracked files to be staged

`git stash list --stat`

`git stash show`

`git stash clear`


GIT Filter branch
-----------------

`git filter-branch --tree-filter {command} --all` : goes to all branches

`git filter-branch --tree-filter HEAD` :  filter only current branch

`git filter-branch --index-filter` :  only in the staging area

`git filter-branch -f --prune-empty -- --all` :  drop commits that dont alter any files


Git Submodule
---------------

`git submodule add`

`git submodule init` : will read .gitmodules files

`git submodule update`

`git push --recurse-submodule=check` : will abort a push if you haven´t pushed a submodule

`git push --recurse-submodule=on-demand` : push all repositories


Git Reflog
---------------

`git reflog` : it´s a secondary log local in our repo

`git log --walk-reflog`


Git Re Re Re
-------------

Git recuse recorded resolution

`git config --global rerere.enable true`


Git Practices 
--------------

Interactive -> use for *simplify history*

FastForward -> less commits

`recursive merge --no-ff`