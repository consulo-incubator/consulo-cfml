<cfset formAction = "a">
<cfoutput>
    <form action="#formAction#">
        <cfloop index="ListElement" list="John,Paul,George,Ringo">
            <fieldset legend="test">
            <h1>
            <cfset as = "w">
                <input name="firstname" type="text" value="firstname"/>
            </h1>

            </fieldset>
            </cfloop>
    </form>
</cfoutput>