# GeoPicSorter
> Geotagged picture organizer based on location groups.

***GeoPicSorter*** is a free and open source tool written in Java, that lets you automatically organize pictures within folders based on groups of geolocations like city or street, the program works with various open source third-party tools and a [reverse geocoding](https://en.wikipedia.org/wiki/Reverse_geocoding) API offered by Geoapify to make the required data requests, operate with the pictures, etc.

Read the next section for some important notes regarding the program's functionality.

## :warning: Important
This is the **first** public release of ***GeoPicSorter*** and is early on development, as such, there's a lot of things to be done in order to improve functionality and stability, implement new features, add visual feedback for picture sorting, etc.
Most notoriously, you're limited by the number of daily requests you can make with a free key and require of a stable internet connectivity, you also don't have a visual display of the progress while the program is working on sorting pictures.

Refer to the TODOs project for more details on the list of missing features to be added, I welcome suggestions and contributions, of course.

Finally, ***GeoPicSorter*** detects only `.jpg` and `.jpeg` files, testing with other formats needs to be done in order to handle them here.

### Tested on
> ***GeoPicSorter*** is known to work on the following OSes and versions, it may not work as intended on other OSes.

* Windows 10 x64.

## What is a geotag?
The [geotag](https://en.wikipedia.org/wiki/Geotagging) is part of the [Exif](https://en.wikipedia.org/wiki/Exif) specification, and put simply, it's a dedicated category from Exif where the user can optionally store, add, edit or remove geolocation data to any picture, most notoriously this includes latitude and longitude, which determine where your picture was taken in the Earth.

## But why?

I have a rather extensive and growing pictures collection, most of which do have geotags so that I can remember where I took a picture and in preparation to organize them, however, I've come to realize that doing this by hand is **time-consuming**, I need to sit down for hours to look at each picture and determine to which folder do I move it to, this problem grows in significance the more specific the organization criteria is, so you can begin to imagine what is it like to order pictures by street hand-by-hand.

Trying to figure out a solution to speed-up and automatize picture organizing would greatly help me to detect potential &laquo;duplicates&raquo; (that is, pictures of a same place taken in different days and on similar conditions such as climate) and easily identify where I took a picture, as I'd order them by:

> Country → State → City → Street (and maybe date)

As a result of this problem, ***GeoPicSorter*** was initially coined around mid-May/2022, as an end-of-course project to fullfill its basic premise, then eventually growing onto a more complete and efficient tool that not only solves a problem of my own but will encourage picture organization and how useful geotagging pictures can be, like for remembering places you've visited or for aiding yourself with some sort of study that requires picturing subjects photographied at various places.

## Why not adding more criterias and making a less niche tool?

The key issue behind that is that there's already tools that organize pictures on some other criterias like timestamps, however, there's not many of them, with my research and personal experience, I've found out about the following and personally tested them with my own geotagged pictures:
* [PhotoMove](https://wethegeek.com/how-to-sort-photos-by-date/)
* [ExifSort](https://www.yawcam.com/exifsort/)
* [Systweak Photo Organizer](https://www.systweak.com/photo-organizer)
* [ImageRanger](https://imageranger.com/tips/how_to_arrange_in_folders/)

All of which are dated, closed source tools, yet these get their job done and have a few different sorting criterias and a common one: by date.

Thing is, while there are tools that do this job, there's very few of them, and at best, your only picture organizer by **geolocation** groups is ImageRanger, which is closed source and has a trial of 10 days then you buy it or uninstall it, but it can organize pictures... by city, and it lacks flexibility for the location criterias, you can only pick city. But on the plus side, it works offline.
Finally, all of them require you to select an output directory, and it can't be the same as the input.

***GeoPicSorter*** aims to be an open source and more flexible alternative one can use freely (their only hard limitations being the daily requests cap and internet connectivity) and contribute to its growth.
And unlike said tools, you can use the same directory for the output, but in the future, you can optionally choose diferent directories for I/O.

Having all of this said, I may consider developing a more featured picture sorter by folder as I learn more, but I feel that ***GeoPicSorter*** will address a specific problem quickly while encouraging usage of geotags, I'm open to suggestions!

## How to use?

Make sure to have [Java](https://www.java.com/en/) installed.
To get ***GeoPicSorter***, simply grab the latest compiled JAR from the [releases](https://github.com/af-a1997/GeoPicSorter/releases) page, it's recommended to place the JAR on a folder dedicated to the program, as it'll create two files during runtime (one database for user preferences and the other for storting information of the pictures to sort).

Before continuing, it's important to note that, while ***GeoPicSorter*** is supplied with a free API key from Geoapify, it's meant to be used for quick start and testing purposes, and will be updated ocassionally to prevent abuse and hard coding on forked projects (the older key will be invalidated).
The catch about this is that with that same key, everyone in total can make up to 3000 daily requests, and up to 5 requests per second, which can be a problem if multiple users use that key, therefore, you're highly encouraged to [create and provide a key of your own](https://myprojects.geoapify.com), simply sign up at Geoapify, create a project and you'll be issued one almost immediately and automatically (it's an alphanumeric string), ***GeoPicSorter*** will also ask to do this upon start.
This is a placeholder feature until I can find a proper solution to unlimited and faster reverse geocoding, it's greatly appreciated if you can provide us such solution, **preferrably offline**.

When you open ***GeoPicSorter***, you'll be greeted with the `MainWindow` JFrame, it's a window where you get to the rest of the areas in this tool, you can press Control+O to open the folder chooser (or find the option in the [Program] menu).

After bringing up the folder chooser, you're able to select a folder where you have pictures, it doesn't matter if they're geotagged or not, ***GeoPicSorter*** detects pictures missing such info, and even pictures taken in places that don't have the location info for the criteria you want, like street names on a rural zone.

The `SorterProcess` JFrame will open, in which the pictures' filenames and the toolbar to control the organizing process as well as setting up the sorting criteria will be at.
Currently you can't abort the sorting process until the program is done, because the sorting process is kind of basic in terms of coding; this also explains why the program seems like it's not responding while in reality, it's making the API requests, **I kindly ask to let the program run until the data appears on screen**, this indicates ***GeoPicSorter*** finished the sorting process.

Before you begin, make sure you're connected to the internet and Java is allowed to send and receive connections, as ***GeoPicSorter*** interacts with an online API to get the location names.
Once you're all set, press the [Start] button and the pictures will be copied to the matching folder(s), by default they're not being moved to prevent data loss during the organization process, and in case you want to restore the original (unsorted) structure of your pictures, so you may want to delete the base pictures afterwards, but this behavior can be changed before starting the sorting process.

When the sorting process is done, you're free to load another folder to work with (close `SortingProcess` first!) or close the program entirely.

### Preferences

The user preferences window aren't finished yet, but you can currently choose if you need ***GeoPicSorter*** to remember your last work directory, the API key to use and the I/O operations to do with the picture files.

## About the GUI

The icons are sourced from [GNOME icon theme 2.10](https://download.gnome.org/sources/gnome-icon-theme/2.10/), and the layout of `AboutWindow` and `Credits` windows has been greatly inspired on those of Linux software from the 2000s such as Gedit.