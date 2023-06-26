# Sha's Shitty Rhythm Game

![](https://cdn.shashack.org/SSRG/gameplay.gif)  
An underdeveloped four-lane, tile rhythm game made as a research project into GUI development (it's pronounced *double S R G*).

## Downloads

You can download the game [here](https://cdn.shashack.org/SSRG/SSRG.jar) (Java is required to run it).  
[These](https://cdn.shashack.org/SSRG/shas_song_pack_1.zip) are some levels I made that you can try out.

# Setup Guide (you'll need it)

#### ***Ensure you have download both the [game](https://cdn.shashack.org/SSRG/SSRG.jar) and [level pack](https://cdn.shashack.org/SSRG/shas_song_pack_1.zip) before proceeding!!!***  

### Launch `SSRG.jar`

First, Launch `SSRG.jar`. It will seem like nothing has happened, but that's only because the game has **crashed**. No worries tho, it's supposed to do that<sup>TM</sup>! A folder should now have been created
on your system that we will need to navigate to...

### Navigate to `AppData`

The aforementioned folder should be located in (for Windows) `C:\\Users\WHATEVER USER YOU ARE\AppData\Roaming`. Once there, find the folder named `SSRG` (if said folder is missing, repeat step one). Open the folder
and then we can move on to...  

### Adding Levels

Upon opening the folder `SSRG` that my <s>virus</s> game created in your `AppData` folder, you should see an empty folder titled `songs`.
Now is when you'll need to find where you downloaded the [level pack](https://cdn.shashack.org/SSRG/shas_song_pack_1.zip) that you downloaded when I told you to earlier. If you're not sloppy and know where said
zip folder is, unzip it. Once unzipped, simply replace the `songs` folder in `SSRG` with the `songs` folder from inside the zip file. After doing so, you can now...

### Launch `SSRG.jar` (again)

***The game will not run if there is not at least one correctly formatted level folder in `songs`***, so make sure you successfully completed the previous step ^^  
Launch `SSRG.jar` (again) and the game should now be running. If you wanna learn how to play or even make your own levels, keep reading. Otherwise, enjoy :3

# How to Play SSRG

SSRG is a four-lane, tiled rhythm game which means that you press one of four keys in order to "hit" the "notes" of a song to the beat of the music. It's like *Piano Tiles* or *Friday Night Funkin'* 
or whatever (I don't actually play rhythm games). I think it's best to just see it in action so check a look:  

![](https://cdn.shashack.org/SSRG/gameplay_ex.gif)

This gif depicts the "game" portion of SSRG, but to get there you must first navigate...

## Menus

Upon launching `SSRG.jar` (assuming you successfully followed the Setup Guide), you will be taken to a screen that looks like this:  

![](https://cdn.shashack.org/SSRG/main_menu.png)  

This is the main menu (you can press `ESC` at any time to close the game). 
The left side of the screen houses a list of every level available to play, while the right side displays the currently selected level. You select levels by left-clicking with your mouse 
on their names in the list of songs. Once selected you can view the song's cover art, name, author, and can either *play the level* or *edit the level* by left-clicking their respective buttons.
Let's start with playing levels...

## Once in Game

### Controls

As you can see, there are four lanes that notes approach from. Each lane is controlled by a key on your keyboard. From left to right, those keys are `D` `F` `J` `K`. Once a note overlaps the lane's "receiver"
(the green box at the bottom of the lane), press the corresponding key to hit the note. It's like you're actually playing music! Keep doing this until the songs ends. Once it does, you'll be unceremoniously
booted back to the main menu. While in game, you can press `ESC` to return to the main menu, or `R` to quick restart the level.

### Scoring/Purpose

Depending on how accurate your timing is when hitting notes, you'll be awarded a point value from 1-20 which will add to your overall score for the song. This is where you get bragging rights and also where 
the challenge of the game arises. Your job as player is to hit all notes as accurately as possible. With the max number of points for each note hit being 20, there is such thing as a *perfect performance*
of any given song. This is what you are to strive for.  

![](https://cdn.shashack.org/SSRG/score_ex.gif)

But where do these levels come from??? Well...

## The Editor

The editor is where levels are made, so ***if you wanna make levels here is where you should be!!*** Here is what the editor looks like:  

![](https://cdn.shashack.org/SSRG/editor.png)

(Press `ESC` at any time to return to the main menu).

### Editor Track and Nodes

The box with a grid pattern is the "editor track" and represents your level's note placements. Left-clicking on an "editor node" will toggle it on/off. Toggle it on if you want a note to appear at that 
point in the song!  

![](https://cdn.shashack.org/SSRG/editor_nodes.gif)

In order to know where in the song you are placing notes, utilize the "timestamps" along the editor track (they reflect the time at which you are at in relation to the song). You can move the editor track forward
or backward using the `arrow keys` or `WASD`. Pressing either `D` or the `right arrow key` will move the track forward, while pressing either 'A' or the 'left arrow key' will move the track backwards. It's like
scrubbing through a video!

### Scrub Speed

How far you move the track is determined by the current "scrub speed". By default the scrub speed is set to 1, meaning you'll only increment the editor track by one note. This can be
increased by pressing either `W` or the `up arrow key`. It will be incremented by a multiple of two each time, until reaching the maximum speed of 16. The speed can be decremented in the same fashion
by pressing either `S` or the `down arrow key`. The minimum speed is 1.

![](https://cdn.shashack.org/SSRG/scrub_speed.gif)

### Audio Playback

Arguably, the most important aspect of the editor, for our purposes anyway, is audio playback. By pressing the `space bar` you can play the level's song from the point at which you are currently at.
Pressing the `space bar` again will pause the audio playback. Pressing `R` will reset the audio playback to the start of the song and will also reset scrub speed back to 1. 
You will know where you are at in the song for playback with the...

### Track Needle

The "track needle" is a cyan line that denotes where in the song audio playback is currently set. The track needle will be moved in time with playback, and can be manually moved by clicking the `left mouse button`.
This is a bit imprecise tho so just bear with it.

### Saving

Hopefully by now you can navigate the editor and start "charting" levels. But it is important to note that ***notes placed are NOT automatically saved!!***
In order to save your work, press that save button located at the top right of the editor track, or press `1`. After doing so, text displaying 'Saving...' will appear above the save button.
Once saving is complete, the text will display 'Saved!' and your level's note data will be saved.

![](https://cdn.shashack.org/SSRG/saving.gif)

### Testing

If you'd like to test your level while working on it in the editor, left click the "test level button" in at the bottom center of the screen. This will start a game instance of your level from the point at which
your playback is currently set. The rounding is a bit messy, so make sure the track needle is on a whole number when doing so. Once in a test instance, you play the game a usual but pressing `ESC` will
return you to the editor.  

![](https://cdn.shashack.org/SSRG/testing.gif)

That's all fine and good, but how do I create new levels??

## Creating New Levels

Since I'm sick of this project already, you have to navigate to the `songs` folder belonging to the `SSRG` folder in your `AppData` folder to create new levels. Once there, create a new folder and name it whatever
you want to show up in the songs list. Inside of that folder needs to be three things:

### Music File

Simply, you need a music file that houses your song. ***Make sure it is in WAV format!!***

### Icon Image

Also simple; you need a square image (of most any file type) that houses the cover art for your level.

### `songData.json`

Much less simple; you need a JSON file with the following information formatted in the following way:  

>{  
>"SongData":{"iconName":"ICON_IMAGE_FILE_NAME.FILE_TYPE","author":"AUTHOR_NAME","name":"LEVEL_NAME","length":LENGTH_OF_SONG,"trackName":"NAME_OF_MUSIC_FILE.wav","bpm":BPM_OF_SONG},  
>"NoteData:[]"  
>}

[Here](https://cdn.shashack.org/SSRG/SongDataTemplate.json) is a template :)  

If you haven't messed anything up, your song should now appear in game and you can now play and edit it to your heart's content.

# Known Issues

- Testing levels in the editor can be finicky if the track needle is not on a whole number.
- The game does not like if you mismatch song length with the length of your music file but, like, I did it and it was fine just be careful.
- You are not returned to the main menu after a level ends if the music file length is less than a second.
- This game is lacking basic features like combos, leaderboards, or a results screen :P
