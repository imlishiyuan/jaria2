package com.bigbrotherlee.jaria2.client.enums;

import java.util.Arrays;

/**
 * RPC操作定义
 * @author lee
 */
public enum ActionEnum {
    /**
     * This method adds a new download.
     * uris is an array of HTTP/FTP/SFTP/BitTorrent URIs (strings) pointing to the same resource.
     * If you mix URIs pointing to different resources, then the download may fail or be corrupted without aria2 complaining.
     * When adding BitTorrent Magnet URIs, uris must have only one element and it should be BitTorrent Magnet URI.
     * options is a struct and its members are pairs of option name and value.
     * See Options below for more details. If position is given, it must be an integer starting from 0.
     * The new download will be inserted at position in the waiting queue.
     * If position is omitted or position is larger than the current size of the queue, the new download is appended to the end of the queue.
     * This method returns the GID of the newly registered download.
     *
     * 此方法添加新的下载。
     * uris 是指向同一资源的 HTTP/FTP/SFTP/BitTorrent URI（字符串）数组。
     * 如果您混合指向不同资源的 URI，则下载可能会失败或损坏，而不会 aria2 抱怨。
     * 添加 BitTorrent Magnet URI 时，uris 必须只有一个元素，并且应该是 BitTorrent Magnet URI。
     * options 是一个结构，它的成员是成对的选项名称和值。有关详细信息，请参阅下面的选项。
     * 如果给出位置，它必须是一个从 0 开始的整数。新的下载将被插入到等待队列中的位置。
     * 如果 position 被省略或 position 大于队列的当前大小，则新的下载将附加到队列的末尾。此方法返回新注册的下载的 GID。
     *
     */
    ADD_URI("aria2.addUri","添加新的下载"),

    /**
     * This method adds a BitTorrent download by uploading a ".torrent" file.
     * If you want to add a BitTorrent Magnet URI, use the aria2.addUri() method instead.
     * torrent must be a base64-encoded string containing the contents of the ".torrent" file.
     * uris is an array of URIs (string). uris is used for Web-seeding.
     * For single file torrents, the URI can be a complete URI pointing to the resource; i
     * f URI ends with /, name in torrent file is added.
     * For multi-file torrents, name and path in torrent are added to form a URI for each file.
     * options is a struct and its members are pairs of option name and value.
     * See Options below for more details.
     * If position is given, it must be an integer starting from 0.
     * The new download will be inserted at position in the waiting queue.
     * If position is omitted or position is larger than the current size of the queue, the new download is appended to the end of the queue.
     * This method returns the GID of the newly registered download.
     * If --rpc-save-upload-metadata is true, the uploaded data is saved as a file named as the hex string of SHA-1 hash of data plus ".torrent" in the directory specified by --dir option.
     * E.g. a file name might be 0a3893293e27ac0490424c06de4d09242215f0a6.torrent.
     * If a file with the same name already exists, it is overwritten!
     * If the file cannot be saved successfully or --rpc-save-upload-metadata is false, the downloads added by this method are not saved by --save-session.
     *
     * 此方法通过上传一个".torrent "文件来添加BitTorrent下载。
     * 如果您想添加BitTorrent Magnet URI，请使用aria2.addUri()方法。
     * torrent必须是一个包含".torrent "文件内容的base64编码的字符串。uris是一个URI数组（字符串）。
     * uris用于网络播种。对于单文件torrent，URI可以是指向资源的完整URI；
     * 如果URI以/结尾，则会添加torrent文件中的名称。对于多文件torrent，torrent中的名称和路径被添加到每个文件的URI中。
     * options是一个结构，其成员是选项名称和值的对。更多细节见下面的选项。如果给出了position，它必须是一个从0开始的整数。
     * 新的下载将被插入到等待队列的位置。如果省略了位置，或者位置大于队列的当前大小，新的下载将被追加到队列的末尾。
     * 该方法返回新注册的下载的GID。
     * 如果 --rpc-save-upload-metadata为真，上传的数据将被保存为一个文件，其名称为数据的SHA-1哈希值加上".torrent "的十六进制字符串，放在由-dir选项指定的目录中。
     * 例如，文件名可能是0a3893293e27ac0490424c06de4d09242215f0a6.torrent。
     * 如果一个具有相同名称的文件已经存在，它将被覆盖!
     * 如果文件不能被成功保存，或者 --rpc-save-upload-metadata为假，由这个方法添加的下载不会被-save-session保存。
     *
     */
    ADD_TORRENT("aria2.addTorrent","添加BitTorrent下载"),

    /**
     * This method adds a Metalink download by uploading a ".metalink" file.
     * metalink is a base64-encoded string which contains the contents of the ".metalink" file.
     * options is a struct and its members are pairs of option name and value.
     * See Options below for more details. If position is given, it must be an integer starting from 0.
     * The new download will be inserted at position in the waiting queue.
     * If position is omitted or position is larger than the current size of the queue, the new download is appended to the end of the queue.
     * This method returns an array of GIDs of newly registered downloads.
     * If --rpc-save-upload-metadata is true, the uploaded data is saved as a file named hex string of SHA-1 hash of data plus ".metalink" in the directory specified by --dir option.
     * E.g. a file name might be 0a3893293e27ac0490424c06de4d09242215f0a6.metalink. If a file with the same name already exists, it is overwritten!
     * If the file cannot be saved successfully or --rpc-save-upload-metadata is false, the downloads added by this method are not saved by --save-session.
     *
     * 该方法通过上传".metalink "文件来添加Metalink下载。
     * metalink是一个base64编码的字符串，包含".metalink "文件的内容。options是一个结构，其成员是一对选项名称和值。
     * 更多细节见下面的选项。如果给出position，它必须是一个从0开始的整数，新的下载将被插入到等待队列的位置。
     * 如果省略了位置，或者位置大于队列的当前大小，新的下载将被追加到队列的末端。该方法返回一个新注册下载的GID数组。
     * 如果 --rpc-save-upload-metadata为真，上传的数据将被保存为一个文件，文件名是数据的SHA-1哈希值加上".metalink "的十六进制字符串，放在-dir选项指定的目录中。
     * 例如，文件名可能是0a3893293e27ac0490424c06de4d09242215f0a6.metalink。
     * 如果一个同名的文件已经存在，它就会被覆盖掉
     * 如果文件不能被成功保存或--rpc-save-upload-metadata为false，由该方法添加的下载不会被-save-session保存。
     */
    ADD_METALINK("aria2.addMetalink","添加metalink下载"),

    /**
     * This method removes the download denoted by gid (string).
     * If the specified download is in progress, it is first stopped. The status of the removed download becomes removed.
     * This method returns GID of removed download.
     *
     * 该方法删除由gid（字符串）表示的下载。
     * 如果指定的下载正在进行中，它首先被停止。
     * 被删除的下载的状态变为删除。该方法返回被删除的下载的GID。
     */
    REMOVE("aria2.remove","删除下载"),

    /**
     * This method removes the download denoted by gid.
     * This method behaves just like aria2.remove() except that this method removes the download without performing any actions which take time, such as contacting BitTorrent trackers to unregister the download first.
     *
     * 此方法将删除由gid表示的下载。
     * 该方法的行为与aria2.remove()相同，只是该方法在移除下载时不需要执行任何需要时间的操作，例如先联系BitTorrent追踪器以取消下载的注册。
     */
    FORCE_REMOVE("aria2.forceRemove","强制删除下载"),

    /**
     * This method pauses the download denoted by gid (string).
     * The status of paused download becomes paused.
     * If the download was active, the download is placed in the front of waiting queue.
     * While the status is paused, the download is not started.
     * To change status to waiting, use the aria2.unpause() method.
     * This method returns GID of paused download.
     *
     * 这个方法暂停了由gid（字符串）表示的下载。
     * 暂停的下载的状态变成暂停的。
     * 如果下载是活动的，则下载被放在等待队列的前面。
     * 当状态是暂停的时候，下载不会开始。
     * 要改变状态为等待，使用aria2.unpause()方法。
     * 该方法返回暂停的下载的GID。
     */
    PAUSE("aria2.pause","暂停下载"),

    /**
     * This method is equal to calling aria2.pause() for every active/waiting download.
     * This methods returns OK.
     *
     * 这个方法相当于为每个正在进行/等待的下载调用aria2.pause()。这个方法返回OK。
     */
    PAUSE_ALL("aria2.pauseAll","暂停全部下载"),

    /**
     * This method pauses the download denoted by gid.
     * This method behaves just like aria2.pause() except that this method pauses downloads without performing any actions which take time, such as contacting BitTorrent trackers to unregister the download first.
     *
     * 此方法暂停由gid表示的下载。
     * 该方法的行为与aria2.pause()类似，只是该方法暂停下载时不执行任何需要时间的操作，
     * 例如先联系BitTorrent跟踪器以取消下载注册。
     */
    FORCE_PAUSE("aria2.forcePause","强制暂停下载"),
    /**
     * This method is equal to calling aria2.forcePause() for every active/waiting download.
     * This methods returns OK.
     *
     * 这个方法相当于为每个正在进行/等待的下载调用aria2.forcePause()。
     * 这个方法返回OK。
     */
    FORCE_PAUSE_ALL("aria2.forcePauseAll","强制暂停全部下载"),

    /**
     * This method changes the status of the download denoted by gid (string) from paused to waiting, making the download eligible to be restarted.
     * This method returns the GID of the unpaused download.
     *
     * 该方法将由gid（字符串）表示的下载状态从暂停变为等待，使下载有资格被重新启动。
     * 该方法返回未暂停的下载的GID。
     */
    UNPAUSE("aria2.unpause","重启下载"),

    /**
     * This method is equal to calling aria2.unpause() for every paused download.
     * This methods returns OK.
     *
     * 这个方法相当于为每个暂停的下载调用aria2.unpause()。这个方法返回OK。
     */
    UNPAUSE_ALL("aria2.unpauseAll","重启全部下载"),

    /**
     * This method returns the progress of the download denoted by gid (string).
     * keys is an array of strings. If specified, the response contains only keys in the keys array.
     * If keys is empty or omitted, the response contains all keys.
     * This is useful when you just want specific keys and avoid unnecessary transfers.
     * For example, aria2.tellStatus("2089b05ecca3d829", ["gid", "status"]) returns the gid and status keys only.
     * The response is a struct and contains following keys. Values are strings.
     *
     * 该方法返回由gid（字符串）表示的下载进度。
     * keys是一个字符串数组。如果指定，响应只包含keys数组中的keys。如果keys为空或省略，则响应包含所有keys。
     * 当你只想要特定的键，避免不必要的转移时，这很有用。例如，aria2.tellStatus("2089b05ecca3d829", ["gid", "status"])只返回gid和status键。
     * 响应是一个结构。
     * <a href="https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellStatus">见文档</a>
     *
     * @see <a href="https://aria2.github.io/manual/en/html/aria2c.html#aria2.tellStatus">见文档</a>
     */
    TELL_STATUS("aria2.tellStatus","查询下载进度"),
    /**
     * This method returns the URIs used in the download denoted by gid (string).
     * The response is an array of structs and it contains following keys.
     * Values are string.
     *
     * 该方法返回由gid（字符串）表示的下载中使用的URI。响应是一个结构数组。
     * <a href="https://aria2.github.io/manual/en/html/aria2c.html#aria2.getUris">见文档</a>
     *
     * @see <a href="https://aria2.github.io/manual/en/html/aria2c.html#aria2.getUris">见文档</a>
     */
    GET_URIS("aria2.getUris","查询下载链接"),

    /**
     * This method returns the file list of the download denoted by gid (string).
     * The response is an array of structs which contain following keys. Values are strings.
     *
     * 该方法返回由gid（字符串）表示的下载文件列表。响应是一个包含以下键的结构数组。
     *
     * <a href="https://aria2.github.io/manual/en/html/aria2c.html#aria2.getFiles">见文档</a>
     *
     * @see <a href="https://aria2.github.io/manual/en/html/aria2c.html#aria2.getFiles">见文档</a>
     */
    GET_FILES("aria2.getFiles","查询下载文件"),

    /**
     * This method returns a list peers of the download denoted by gid (string).
     * This method is for BitTorrent only.
     * The response is an array of structs and contains the following keys. Values are strings.
     *
     * 该方法返回一个由gid（字符串）表示的下载的节点列表。
     * 该方法仅适用于BitTorrent。响应是一个结构数组。
     */
    GET_PEERS("aria2.getPeers","查询下载peer"),

    /**
     * This method returns currently connected HTTP(S)/FTP/SFTP servers of the download denoted by gid (string).
     * The response is an array of structs and contains the following keys. Values are strings.
     *
     * 该方法返回当前连接的HTTP(S)/FTP/SFTP服务器，这些服务器是由gid（字符串）表示的下载。
     * 响应是一个结构数组。
     */
    GET_SERVERS("aria2.getServers","查询下载服务器"),

    /**
     * returned by the aria2.tellStatus() method.
     * For the keys parameter, please refer to the aria2.tellStatus() method.
     *
     * 该方法返回一个正在进行的下载列表。
     * 由aria2.tellStatus()方法返回。关于键的参数，请参考aria2.tellStatus()方法。
     */
    TELL_ACTIVE("aria2.tellActive","查询正在下载的列表"),

    /**
     * This method returns a list of waiting downloads, including paused ones.
     * offset is an integer and specifies the offset from the download waiting at the front.
     * num is an integer and specifies the max. number of downloads to be returned.
     * For the keys parameter, please refer to the aria2.tellStatus() method.
     *
     * If offset is a positive integer, this method returns downloads in the range of [offset, offset + num).
     *
     * offset can be a negative integer.
     * offset == -1 points last download in the waiting queue and offset == -2 points the download before the last download, and so on.
     * Downloads in the response are in reversed order then.
     *
     * For example, imagine three downloads "A","B" and "C" are waiting in this order.
     * aria2.tellWaiting(0, 1) returns ["A"]. aria2.tellWaiting(1, 2) returns ["B", "C"].
     * aria2.tellWaiting(-1, 2) returns ["C", "B"].
     *
     * The response is an array of the same structs as returned by aria2.tellStatus() method.
     *
     * 此方法返回等待下载的列表，包括暂停的下载。 offset 是一个整数，指定从前面等待下载的偏移量。
     * num 是一个整数并指定最大值。要返回的下载次数。 keys 参数请参考 aria2.tellStatus() 方法。
     *
     * 如果 offset 为正整数，则此方法返回 [offset, offset + num) 范围内的下载。
     * 偏移量可以是负整数。 offset == -1 点等待队列中的最后一次下载，offset == -2 点在最后一次下载之前的下载，依此类推。那么响应中的下载顺序相反。
     *
     * 例如，假设三个下载“A”、“B”和“C”按此顺序等待。
     * aria2.tellWaiting(0, 1) 返回 ["A"]。 aria2.tellWaiting(1, 2) 返回 ["B", "C"]。 aria2.tellWaiting(-1, 2) 返回 ["C", "B"]。
     * 响应是由 aria2.tellStatus() 方法返回的相同结构的数组。
     */
    TELL_WAITING("aria2.tellWaiting","查询等待列表"),

    /**
     * This method returns a list of stopped downloads.
     * offset is an integer and specifies the offset from the least recently stopped download.
     * num is an integer and specifies the max. number of downloads to be returned.
     * For the keys parameter, please refer to the aria2.tellStatus() method.
     *
     * offset and num have the same semantics as described in the aria2.tellWaiting() method.
     *
     * The response is an array of the same structs as returned by the aria2.tellStatus() method.
     *
     * 该方法返回一个停止下载的列表。
     * offset是一个整数，指定从最近停止的下载的偏移量。num是一个整数，指定要返回的最大下载数。
     * 对于key参数，请参考aria2.tellStatus()方法。
     * offset和num的语义与aria2.tellWaiting()方法中描述的相同。
     * 响应是一个数组，其结构与aria2.tellStatus()方法返回的相同。
     */
    TELL_STOPPED("aria2.tellStopped","查询停止下载的列表"),

    /**
     * This method changes the position of the download denoted by gid in the queue.
     * pos is an integer. how is a string.
     * If how is POS_SET, it moves the download to a position relative to the beginning of the queue.
     * If how is POS_CUR, it moves the download to a position relative to the current position.
     * If how is POS_END, it moves the download to a position relative to the end of the queue.
     * If the destination position is less than 0 or beyond the end of the queue, it moves the download to the beginning or the end of the queue respectively.
     * The response is an integer denoting the resulting position.
     *
     * For example, if GID#2089b05ecca3d829 is currently in position 3, aria2.changePosition('2089b05ecca3d829', -1, 'POS_CUR') will change its position to 2. Additionally aria2.changePosition('2089b05ecca3d829', 0, 'POS_SET') will change its position to 0 (the beginning of the queue).
     *
     * The following examples move the download GID#2089b05ecca3d829 to the front of the queue.
     *
     * 这个方法改变由gid表示的下载在队列中的位置，pos是一个整数，how是一个字符串。
     * 如果how是POS_SET，它将下载移动到相对于队列开始的位置。
     * 如果how是POS_CUR，它将下载移动到相对于当前位置的位置。
     * 如果how是POS_END，它将下载移动到相对于队列末端的位置。
     * 如果目标位置小于0或超过队列的末端，它将下载分别移动到队列的开始或末端。响应是一个整数，表示结果的位置。
     * 例如，如果GID#2089b05ecca3d829目前在位置3，aria2.changePosition('2089b05ecca3d829', -1, 'POS_CUR')将把它的位置改为2。另外aria2.changePosition('2089b05ecca3d829', 0, 'POS_SET')将把它的位置改为0（队列的开头）。
     * 下面的例子将下载GID#2089b05ecca3d829移到队列的前面。
     */
    CHANGE_POSITION("aria2.changePosition","改变下载顺序"),

    /**
     * This method removes the URIs in delUris from and appends the URIs in addUris to download denoted by gid.
     * delUris and addUris are lists of strings. A download can contain multiple files and URIs are attached to each file.
     * fileIndex is used to select which file to remove/attach given URIs. fileIndex is 1-based.
     * position is used to specify where URIs are inserted in the existing waiting URI list.
     * position is 0-based. When position is omitted, URIs are appended to the back of the list.
     * This method first executes the removal and then the addition.
     * position is the position after URIs are removed, not the position when this method is called.
     * When removing an URI, if the same URIs exist in download, only one of them is removed for each URI in delUris.
     * In other words, if there are three URIs http://example.org/aria2 and you want remove them all, you have to specify (at least) 3 http://example.org/aria2 in delUris.
     * This method returns a list which contains two integers. The first integer is the number of URIs deleted. The second integer is the number of URIs added.
     *
     * 这个方法从delUris中移除URI，并将addUris中的URI添加到由gid表示的下载中。delUris和addUris是字符串的列表。
     * 一个下载可以包含多个文件，URI被附加到每个文件上。 fileIndex用于选择哪个文件来移除/附加给定的URI。当位置被省略时，URI将被追加到列表的后面。
     * 这个方法首先执行删除，然后再进行添加。位置是URI被删除后的位置，而不是调用这个方法时的位置。
     * 在删除URI时，如果下载中存在相同的URI，那么delUris中的每个URI只删除其中一个。
     * 换句话说，如果有三个URI http://example.org/aria2，你想把它们全部删除，你必须在delUris中指定（至少）3个http://example.org/aria2。
     * 这个方法返回一个包含两个整数的列表。第一个整数是被删除的URI的数量。第二个整数是添加的URI的数量。
     *
     */
    CHANGE_URI("aria2.changeUri","更新下载链接"),

    /**
     * This method returns options of the download denoted by gid.
     * The response is a struct where keys are the names of options. The values are strings.
     * Note that this method does not return options which have no default value and have not been set on the command-line,
     * in configuration files or RPC methods.
     *
     * 该方法返回由gid表示的下载的选项。响应是一个结构，其中键是选项的名称。值是字符串。
     * 注意，此方法不返回没有默认值的选项，也没有在命令行、配置文件或RPC方法中设置。
     */
    GET_OPTION("aria2.getOption","查询某下载参数"),

    /**
     * This method changes options of the download denoted by gid (string) dynamically. options is a struct.
     * The options listed in Input File subsection are available, except for following options:
     *
     * 1. dry-run
     * 2. metalink-base-uri
     * 3. parameterized-uri
     * 4. pause
     * 5. piece-length
     * 6. rpc-save-upload-metadata
     *
     * Except for the following options,
     * changing the other options of active download makes it restart
     * (restart itself is managed by aria2, and no user intervention is required):
     *
     * 1. bt-max-peers
     * 2. bt-request-peer-speed-limit
     * 3. bt-remove-unselected-file
     * 4. force-save
     * 5. max-download-limit
     * 6. max-upload-limit
     *
     * This method returns OK for success.
     *
     * 这个方法动态地改变由gid（字符串）表示的下载的选项。输入文件小节中列出的选项是可用的，但以下选项除外。
     *
     * 1. dry-run
     * 2. metalink-base-uri
     * 3. parameterized-uri
     * 4. pause
     * 5. piece-length
     * 6. rpc-save-upload-metadata
     *
     * 除了以下选项，改变活动下载的其他选项会使其重新启动（重新启动本身由aria2管理，不需要用户干预）。
     *
     * 1. bt-max-peers
     * 2. bt-request-peer-speed-limit
     * 3. bt-remove-unselected-file
     * 4. force-save
     * 5. max-download-limit
     * 6. max-upload-limit
     * 该方法成功时返回OK。
     *
     */
    CHANGE_OPTION("aria2.changeOption","更新下载参数"),

    /**
     * This method returns the global options.
     * The response is a struct. Its keys are the names of options.Values are strings.
     * Note that this method does not return options which have no default value and have not been set on the command-line, in configuration files or RPC methods.
     * Because global options are used as a template for the options of newly added downloads, the response contains keys returned by the aria2.getOption() method.
     *
     * 该方法返回全局选项。响应是一个结构。
     * 它的键是选项的名称。值是字符串。
     * 注意，该方法不返回没有默认值的选项，也没有在命令行、配置文件或RPC方法中设置。
     * 因为全局选项被用作新添加的下载选项的模板，响应包含由aria2.getOption()方法返回的键。
     *
     */
    GET_GLOBAL_OPTION("aria2.getGlobalOption","获取全局下载参数"),

    /**
     * This method changes global options dynamically. options is a struct.
     * The following options are available:
     * bt-max-open-files
     * download-result
     * keep-unfinished-download-result
     * log
     * log-level
     * max-concurrent-downloads
     * max-download-result
     * max-overall-download-limit
     * max-overall-upload-limit
     * optimize-concurrent-downloads
     * save-cookies
     * save-session
     * server-stat-of
     *
     * In addition, options listed in the Input File subsection are available, except for following options:
     * checksum, index-out, out, pause and select-file.
     *
     * With the log option, you can dynamically start logging or change log file.
     * To stop logging, specify an empty string("") as the parameter value.
     * Note that log file is always opened in append mode. This method returns OK for success.
     *
     * 该方法动态地改变全局选项，选项是一个结构。以下是可用的选项。
     * bt-max-open-files
     * download-result
     * keep-unfinished-download-result
     * log
     * log-level
     * max-concurrent-downloads
     * max-download-result
     * max-overall-download-limit
     * max-overall-upload-limit
     * optimize-concurrent-downloads
     * save-cookies
     * save-session
     * server-stat-of
     *
     * 此外，输入文件小节中列出的选项都是可用的，除了以下选项：checksum, index-out, out, pause and select-file.
     * 通过log选项，你可以动态地启动日志记录或改变日志文件。要停止日志记录，指定一个空字符串（""）作为参数值。
     * 注意，日志文件总是以追加模式打开。该方法成功时返回OK。
     */
    CHANGE_GLOBAL_OPTION("aria2.changeGlobalOption","更新全局参数"),

    /**
     * This method returns global statistics such as the overall download and upload speeds.
     * The response is a struct and contains the following keys. Values are strings.
     *
     * 该方法返回全局统计数据，如总体下载和上传速度。响应是一个结构，包含以下键。值是字符串。
     */
    GET_GLOBAL_STAT("aria2.getGlobalStat","获取全局统计数据"),

    /**
     * This method purges completed/error/removed downloads to free memory. This method returns OK.
     *
     * 此方法清除已完成/错误/已删除的下载以释放内存。此方法返回 OK。
     */
    PURGE_DOWNLOAD_RESULT("aria2.purgeDownloadResult","清除终态下载"),

    /**
     * This method removes a completed/error/removed download denoted by gid from memory.
     * This method returns OK for success.
     *
     * 该方法从内存中删除一个由gid表示的已完成/错误/删除的下载。该方法成功时返回OK。
     */
    REMOVE_DOWNLOAD_RESULT("aria2.removeDownloadResult","清除某个终态下载"),

    /**
     * This method returns the version of aria2 and the list of enabled features.
     * The response is a struct and contains following keys.
     *
     * version：Version number of aria2 as a string.
     * enabledFeatures: List of enabled features. Each feature is given as a string.
     *
     * 该方法返回 aria2 的版本和启用的功能列表。响应是一个结构，包含以下键。
     * version: aria2的版本号，是一个字符串。
     * enabledFeatures: 启用的功能列表。每个功能都是一个字符串。
     *
     */
    GET_VERSION("aria2.getVersion","获取版本信息"),

    /**
     * This method returns session information. The response is a struct and contains following key.
     * sessionId：Session ID, which is generated each time when aria2 is invoked.
     *
     * 此方法返回会话信息。响应是一个结构并包含以下键。
     * sessionId：会话 ID，每次调用 aria2 时生成。
     */
    GET_SESSION_INFO("aria2.getSessionInfo","获取会话信息"),

    /**
     * This method shuts down aria2. This method returns OK.
     * 关闭aria2 该方法返回OK
     */
    SHUTDOWN("aria2.shutdown","关闭服务"),

    /**
     * This method shuts down aria2().
     * This method behaves like :'aria2.shutdown` without performing any actions which take time,
     * such as contacting BitTorrent trackers to unregister downloads first.
     * This method returns OK.
     *
     * 此方法将关闭aria2()。该方法的行为类似于:'aria2.shutdown'，没有执行任何需要时间的操作，
     * 例如先联系BitTorrent追踪器以取消下载注册。
     * 该方法返回OK。
     */
    FORCE_SHUTDOWN("aria2.forceShutdown","强制关闭服务"),

    /**
     * This method saves the current session to a file specified by the --save-session option.
     * This method returns OK if it succeeds.
     *
     * 该方法将当前会话保存到由-save-session选项指定的文件中。如果成功，该方法返回OK。
     */
    SAVE_SESSION("aria2.saveSession","保存会话"),

    /**
     * This methods encapsulates multiple method calls in a single request.
     * methods is an array of structs.The structs contain two keys:
     * methodName and params.
     * methodName is the method name to call and params is array containing parameters to the method call.
     * This method returns an array of responses.
     * The elements will be either a one-item array containing the return value of the method call or a struct of fault element if an encapsulated method call fails.
     *
     * 这个方法在一个请求中封装了多个方法调用。
     * 方法是一个结构数组。
     * methodName是要调用的方法名称，params是包含方法调用参数的数组。
     * 该方法返回一个响应数组。这些元素要么是包含方法调用的返回值的单项数组，要么是在封装的方法调用失败时的故障元素的结构。
     *
     */
    MULTI_CALL("system.multicall","多重调用"),

    /**
     * This method returns all the available RPC methods in an array of string.
     * Unlike other methods, this method does not require secret token.
     * This is safe because this method just returns the available method names.
     *
     * 该方法以一个字符串数组的形式返回所有可用的RPC方法。
     * 与其他方法不同，这个方法不需要秘密令牌。
     * 这很安全，因为这个方法只是返回可用的方法名称。
     */
    LIST_METHODS("system.listMethods","获取支持的方法"),

    /**
     * This method returns all the available RPC notifications in an array of string.
     * Unlike other methods, this method does not require secret token.
     * This is safe because this method just returns the available notifications names.
     *
     * 这个方法以一个字符串数组的形式返回所有可用的RPC通知。
     * 与其他方法不同，这个方法不需要秘密令牌。
     * 这很安全，因为这个方法只是返回可用的通知名称。
     */
    LIST_NOTIFICATIONS("system.listNotifications","获取支持的通知"),


    /**
     * 非官方，其他方法
     */
    OTHER("#other","其他"),


    ;

    public final String name;
    public final String description;

    ActionEnum(String name,String description){
        this.name = name;
        this.description = description;
    }

    public static final ActionEnum parseByName(String name){
        return Arrays.stream(ActionEnum.values()).filter(e->e.name.equals(name)).findFirst().orElse(OTHER);
    }

}
