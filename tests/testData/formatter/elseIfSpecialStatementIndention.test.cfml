<cfscript>
if (row.duration == 0)
    html += 'Permanently';
else if (row.duration == -1)
    html += 'For this session';
else if (row.duration == 1)
    html += row.duration + ' day';
else
    html += row.duration + ' days';
</cfscript>