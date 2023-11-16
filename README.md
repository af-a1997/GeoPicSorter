> :warning: Repository will soon be archived, the code is being re-written, read [below](#arrows_clockwise-code-re-write-and-rebrand) for more information!

# GeoPicSorter
> :round_pushpin: Geotagged picture organizer based on location groups.

***GeoPicSorter*** is a free and open source tool written in Java, that automatizes picture organization within folders, based on groups of real-life locations on the Earth, such as cities or streets.

The program works with various open source third-party tools and a [reverse geocoding](https://en.wikipedia.org/wiki/Reverse_geocoding) API offered by Geoapify to make the required data requests, operate with the pictures, etc.

Read the next section for some important notes regarding the program's functionality.

## :warning: Important
***GeoPicSorter*** is early on development, and because of this, there's a lot of things to be done in order to improve its functionality and stability, implement new features, add visual feedback for picture sorting, etc.

Most notoriously, you're limited by the number of daily requests you can make with a free key, and require of a stable internet connection, you also don't have a visual display of the progress while the program is working on sorting pictures, **which makes it look like its frozen while it's actually working in the background**.

Refer to the TODOs project for more details on the list of missing features to be added, I welcome suggestions and contributions, of course.

***GeoPicSorter*** detects only `.jpg` and `.jpeg` files. The re-write will bring support to more formats known to support embedded Exif metadata.

### :white_check_mark: Tested on
> :information_source: ***GeoPicSorter*** has been tested to work on the following OSes and architectures.

* Windows 10 x64.

## :information_source: More information

Explanation on the inner workings and credits to utilized tools, as well as any other information related to ***GeoPicSorter*** are all available at [the software's homepage](https://af-a1997.github.io/pages/programs/GeoPicSorter).

## :arrows_clockwise: Code re-write and rebrand

First of: the project is **not** dead! I've been looking into what to re-write the code due to the way GUI code is generated by NetBeans, making of the code itself harder to mantain.

The tool is now being re-written in Python, and will come with a few new extras, as well as a new website, and a re-brand to the project itself, so that the name can be shortened easily.

News will be posted on the project's website [here](https://af-a1997.github.io/pages/programs/GeoPicSorter/), as well as this file.

## :eye: About the GUI

The icons are sourced from [GNOME icon theme 2.10](https://download.gnome.org/sources/gnome-icon-theme/2.10/), and the layout of `AboutWindow` and `Credits` windows has been greatly inspired on those of Linux software from the 2000s, such as Gedit.