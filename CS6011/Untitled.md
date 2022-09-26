## Unix Permissions

d rwx r-x ---

- d - directory
- owner's permissions
- group's permissions (try running groups in cmd)
- everyone else
- % chmod go+rX images # change permissions for group and other to read and execute
  - capital X???

## Home Directory

- `echo $HOME`
- `cd $HOME / cd ~ / cd`: enter home directory
- `echo $PATH`:what is $PATH? list of dire the computer will look through to find commands you run
- `echo $SHELL`
- `env`: environment variables in your system 