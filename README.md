# Commands

```
-> % ascii-generator --help

ascii-generator - Usage: ascii-generator [OPTIONS...] image/file/path.jpg

Available Options:
    --invert, -i           Invert color so that the brightest pixels use the denses characters
    --remove-border, -b    Removes border that sometimes appears on non-inverted images
    --to-file, -f VAL      Output to file, takes output filepath as VAL
    --scaling, -s VAL      Used to scale the images height and width evenly by VAL
    --width, -w VAL        Used to scale the height separately from width. Both must be given
    --height, -h VAL       Used to scale the height separately from width. Both must be given
    --version, -v          Print version number
    --help, -H             Print help menu
```

Scaling factor values are floating point values that indicate the amount to scale the original image by.

Ex. `ascii-generator --width 0.2 --height 0.1 image.png` will scale the ascii-art output's width down to 20% and height to 10% the original image.png's pixel resolution. This is equivalent to `ascii-generator -w 0.2 -h 0.1 image.png` and `ascii-generator -wh 0.2 0.1 image.png` using short flags

Ex. `ascii-generator --invert --scaling 0.015 high-res-image.png` will scale both the height and width of high-res-image.png to 1.5% the original image's pixel resolution, as well as invert the color scale, so that bright colors to use the densest characters rather than vice-versa. This is equivalent to `ascii-generator -is 0.015 high-res-image.png`.

# Installation/Instructions

## Linux Packages

Packages are available for .deb and .rpm based package managers.

Debian/Ubuntu/.deb based [download here.](https://github.com/SuperRonJon/JavaAsciiGenerator/releases/latest/download/ascii-generator-any.deb) or run the following commands...

```sh
curl -LO https://github.com/SuperRonJon/JavaAsciiGenerator/releases/latest/download/ascii-generator-any.deb
sudo apt install ./ascii-generator-any.deb
```

Fedora/CentOS/RHEL/.rpm based [download here.](https://github.com/SuperRonJon/JavaAsciiGenerator/releases/latest/download/ascii-generator-any.rpm) or run the following commands...

```sh
curl -LO https://github.com/SuperRonJon/JavaAsciiGenerator/releases/latest/download/ascii-generator-any.rpm
sudo dnf install ./ascii-generator-any.rpm
```

## Install script

Run the following install script in Git Bash on Windows or linux terminal and it will install ascii-generator to your ~/bin folder. Ensure that your home's bin folder (`~/bin` or `/home/<username>/bin`) is on your path (Step 2 in Command Line Installation Instructions below), and the `ascii-generator` command should work.

Install script requires curl and unzip to be installed to work. If needed run appropraite pre-requisite install command for your system.

Debian/Ubuntu: `sudo apt install curl unzip` 

Fedora/RHEL: `sudo dnf install curl unzip`

Arch/Msys: `pacman -S --needed curl unzip`

Then run install script

```
sh -c "$(curl -fsSL https://raw.githubusercontent.com/SuperRonJon/JavaAsciiGenerator/master/scripts/download-ascii-generator.sh)"
```

Requires Java JRE 8+ to be installed to run after installation.

## Manual Installation

Download zip from [latest release here](https://github.com/SuperRonJon/JavaAsciiGenerator/releases/).

You can run the jar itself manually like this

`java -jar ./path/to/ascii-generator.jar -is 0.3 ./path/to/image.png` which will scale image.png down to 30% original resolution and then output the text to the terminal. Scaling down images is important for high resolution images to be properly viewed in the terimanl or text editor.

New releases comes with zip file that also includes .bat and .sh scripts to easier run the jar as its own command from the command line from anywhere, like this `ascii-generator -is 0.3 ./path/to/image.png`. Instructions for this are below if needed. However generally, just unzip the jar and .bat/.sh file (windows/linux+mac respectively) to a folder on the PATH and run ascii-generator via the script from the command line.

Requires Java JRE 8+

## Command Line Installation Instructions

- unzip contents (jar and .bat/.sh file, .bat is for windows) to your home bin folder, `~/bin`, create one if needed. (`C:\Users\<your-user-name>\bin`)
- add that bin folder (`C:\Users\<your-user-name>\bin`) to your PATH. (On windows search->Edit environment variables for your account->User variables -> Path -> Edit... -> New -> `%USERPROFILE%\bin\`)
- On linux you can just put the .sh and .jar files in `/usr/local/bin/` or some other location on your PATH and remove the .sh (`sudo mv ./path/to/ascii-generator.sh /usr/local/bin/ascii-generator`) and make it executable (`sudo chmod +x /usr/local/bin/ascii-generator`)
- Now restart terminal and command should run with `ascii-generator -is 0.2 /path/to/image.png` from anywhere in the terminal.


![WindowsTerminal_oaIjW7fMUd](https://github.com/user-attachments/assets/7b8bb38a-e663-43d6-9183-f80794eac211)
