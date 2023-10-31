---
layout: page
title: et's Project Portfolio Page
---

### Project: Npc_Track

Npc_Track is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) while still
having the benefits of a Graphical User Interface (GUI). If you can type fast, Npc_Track can get your contact
management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

### Code Contributions

- [Link to My Code Contributions on tP Code Dashboard](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=et-irl&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22)

### Enhancements Implemented

- Added a Comment Field associated with every person.
- Renamed the package associated with tags and comments to `fields`, for better code readability and maintainability.
- Colour-coded and differentiated tags, comments and assignments in the UI.
- Made the font-size, colours and design of the UI more harmonious.
- Made the attendance look more intuitive on the UI.
- Made the telegram weblink more integrated with the UI.
- Implemented assignments, so each student can have an arbitrary amount of assignments.
- Implemented commands relating to assigning and deassigning assignments from students.
  * `assign`
  * `deassign`
  * `assignIndiv`
- Removed the intrusive help window and made the website open immediately.
- Removed useless top bar, as it takes up precious vertical space. The app is optimised for CLI users, so it makes no sense for a clickable bar.
<<<<<<< Updated upstream
- Fix Optionals to accept nulls using `.ofNullable()` instead of `.of()` to ensure code robustness.
- Fix assignment error throwing, wrongly placed in the constructor.
- Fix integer parsing issue, when a failed parse is detected, we should throw the specific error.
=======
<<<<<<< HEAD
>>>>>>> Stashed changes
- Added test cases and sanity check of participation points, making sure overflow errors do not occur.
=======
- Fix assignment error throwing, wrongly placed in the constructor.
- Fix integer parsing issue, when a failed parse is detected, we should throw the specific error.
>>>>>>> c1bc99bc29164f62143542a7be1d655c0d416e9a

### Contributions to the User Guide (UG)

- Updated the `add` command to reflect the new command accepting a comment.
- Updated the `edit` command to reflect the new command accepting a comment.
- Added the `assign` command to give all students an assignment.
- Added the `deassign` command usage.
- Added the `assignIndiv` command usage.
- Spotted documentation bugs and ensured the reliability of the table of contents.
- Scrutinised the grammar and English use in the guide.
- Made examples saner, such as alphabetising the names.

### Contributions to the Developer Guide (DG) and UML Diagrams

- Highlighted the MSS and use cases of command commands such as `add` and `list`.
- Wrote about the implementation of `Assignment`s, and alternatives considered when assigning `Assignment`s to people.

### Contributions to Team-Based Tasks

- Brainstormed and came up with user stories and steered the project in a clear direction.

### Review and Mentoring Contributions

- Reviewed and spotted PRs with issues such as misnamed commit messages.
- Ensured that the documentation associated with a PR is updated correctly.
- Added a pull request template to ensure less regressions are introduced.

### Contributions Beyond the Project Team

- Brought morale to the team.
