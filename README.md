# Test_Vayetek (`Question 1`)

- **Technical assessment**: `sum of étalonnage values` from a text file.
- **Technology Used**: `Java` with `JDK 17`
- **IDE Used**: `IntelliJ IDEA Community Edition`  V2023.1.2

## Problem Solving Question & What are 'étalonnage values'?

The "étalonnage value" is calculated by combining the first and last digits of each line (if present) to form a two-digit integer.

```bash
Question 1

Le document d'étalonnage nouvellement amélioré se compose de lignes de texte ; chaque
ligne contient initialement une valeur d'étalonnage spécifique que les lutins doivent
maintenant récupérer. Sur chaque ligne, la valeur d'étalonnage peut être trouvée en
combinant le premier chiffre et le dernier chiffre (dans cet ordre) pour former un nombre de
deux chiffres.

Par exemple :
1abc2 
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

Dans cet exemple, les valeurs d'étalonnage de ces quatre lignes sont 12, 38, 15 et 77.
En les ajoutant, on obtient 142.
Considérez l'ensemble de votre document d'étalonnage à la fin de ce document.
```

---

## Data (Input/Output):

- **Input Type**: `file: resources/document.txt` Contains lines of text with potential numeric values.
- **Output Required**: The **sum** of all "étalonnage values" found in the document.

---

## Solution:

### Edge Cases:

- **Single digit**: Considered as both the first and last digit.
- **No digits or empty lines**: Considered as `0` (no impact on the sum).
- **`All digits are 0` or `first digit is 0`**: no issue, `0*10 + 0 = 0` `0*10 + x = x`.

### Algorithm Steps

1. `Open` the text file in `read mode`.
2. `Read` the file `line by line`.
3. For each line:
   - `Reset` the `digits PlaceHolder` to ensure it only holds digits from the current line.
   - `Extract digits` (if present) and store them in the digits placeholder (`placeHolder must ensure order on insert`).
     - Handling cases:
       - `single digit`: last_digit = first_digit
       - `totalDigits >= 2`: first_disit = first_found_digit ; last_digit = last_found_digit
       - `totalDigits = 0`: return `0` (Does not affect the sum);
   - Calculate the "étalonnage value" by combining the `first_Digit *10` + `last digits` and return it.
4. Sum all étalonnage values.
5. Print the final sum.

### Why These Choices

- **`try-with-resources` usage?**
> 
>> **Automatic Resource Management**: The file reader is automatically closed after use, even if an exception occurs, preventing potential resource leaks.
>> 
>> **Readability and Simplicity**: Eliminates the need for explicit `close()` calls.
>> 
>> **Best Practice** in modern Java for resource management.

- **`StringBuilder` instead of `char` or `string`?**

>> **Efficiency**: `StringBuilder` allows appending characters without creating new objects, unlike `String`, which is immutable. This reduces memory allocation and garbage collection overhead.
>>
>> **Readability**: Simplifies digit extraction compared to a `char`-based approach, avoiding complex `if` conditions.

- **`firstDigit*10 + lastigit` instead of `char concatenation to String` approach?**
>> **Efficiency**: Avoids the overhead of concatenating characters into a string and then converting the string to an integer. This reduces memory usage and garbage collection overhead.

- **`functional programming` instead of loops?**

>> **Readability and Simplicity**: Although loops may offer slightly better performance, functional programming (Ex: `mapToInt`) makes the code shorter and easier to understand.
>>
>> **TradeOff**:  Traditional loops might offer better performance, but the difference here is negligible.


## Java Implementation:

```java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    private static int getEtalonnageValue(String line, StringBuilder digitsHolder){

        digitsHolder.setLength(0);

        for (int i=0 ; i<line.length(); i++)
            if(Character.isDigit(line.charAt(i)))
                digitsHolder.append(line.charAt(i));

        if(digitsHolder.isEmpty())
            return 0;

        int firstDigit =  Character.getNumericValue(digitsHolder.charAt(0));
        int lastDigit =  Character.getNumericValue(digitsHolder.charAt(digitsHolder.length()-1));

        return firstDigit*10 + lastDigit;
    }
    
    
    public static void main(String[] args) throws IOException {

        String filePath = "resources/document.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){

            StringBuilder reusableDigitHolder = new StringBuilder();
            int sum = br.lines()
                    .mapToInt( line-> getEtalonnageValue(line,reusableDigitHolder))
                    .sum();

            System.out.println("\n\nSum : "+sum);

        }catch (IOException e){
            log.warning("Error occurred when manipulating file : "+e.getMessage());
            throw new IOException(e.getCause());
        }
    }
    
}
```

### Solution Output 
```bash
"C:\Program Files\Java\jdk-17\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.2\lib\idea_rt.jar=5292:C:\Program Files\JetBrains\IntelliJ IDEA 2023.3.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\rbaih\OneDrive\Documents\cvs\Technical_tests\Vayetek\Test_Vayetek_Question_1_Sum_Etalonnage_Values\out\production\Test_Vayetek_Question_1_Sum_Etalonnage_Values Main


Sum : 53386

Process finished with exit code 0
```

---

## Eétalonnage Document Data `resources/document.txt`:

```text
ckmb52fldxkseven3fkjgcbzmnr7
gckhqpb6twoqnjxqplthree2fourkspnsnzxlz1
2onetwocrgbqm7
frkh2nineqmqxrvdsevenfive
four9two
six7sixqrdfive3twonehsk
xkvsone2
one65
rggxsff1seven
djbcgrrtqdshpqqzj43rgcr
br89fivetwoqggnxjfourtl3
zoneight47five5sixjxd74
4five1
5seveneighteightzzbnzsvdjnkvndsxlttfour
htdcmsl12ninethreepkqtdlvtl
twocghtvtdlfchfqnjhrfour19
rptwofiveonecvlldmppxtrvj3
6gqsvsqpzxj
5twomgkzsvg
4ninedflntfsn1
4threethree
43two6eight9
4gqnkntjthree9one45
9lmjgqnkxqvfrzhbcfrlltjxjlkjfrlmb3
ninethreejkcbplfg4kseventwo
mbkfgktwolbvsptgsixseven1oneightzvm
m5fvqfkkk
tptqnsxmsevencrkdmxms55zrfmpmzv7lzqlnmbkzt
bqccqhbdgeight7
four7nine1eight
8fivethndnpztzninepdkfive8
1sixxvqdfourlnpdrfbnnx41vhqgth
sgpkgdb9
fivetwoq7cdprnjdjhs3three9
1mfvptbhqshblvvvdl
ztftqgbzld8jkcmsrdfzonemzdmbzxpfour
dfkctqcjbmfourhszmlvtkeightthree2
4onersrfjcskckcxcj4sevenzzzpsixeight
1fourr5gxpone
seven74ninersgvvmtgsix
jvtwonefivetwosbpglbx2two
fourfivecxgssssixtwofour4
3zcgcjdcpbgqdfnhxtneight
fourfour29cpbccthree44
6six9foureight5xmfdf
four77
tqtnjshdmtwo3four8one27
one52
nine32five3six
qcjbjrpqdtpvveightpmzs17fourjvghvl
5293gmtdttfpmrlhvlt9
2zonenfglkskxhn3gxbrtxtcpfive
three5cfourfvmqlnhp4two
three4knrdzxpr
fmpvqkxgeightthreebdrng9tdcffvsfctwo
jpktgx8fivegqeight5three
7sevennine
3two3
nfthhzcq2
plmbtrzbnbmt1nine
149hvjbktkmbj8mtnrblcvfive7seven
bfthree1
six6kqprz86two6
jthree6
nine2hzrlcqrt7eight
nnzhkhpdb8rbnqmxrfive
rkszqnp1
t1
5sixlmxkgmxrgveight3
sevenlptpdhtjpgxconedvtrrnngn8
nlpvxbscttbbpgndnc7jvrtcjxbsxnqvfcxdcfrgtrdkjflzlrqcnmfourkznnmrv
7kllgxpb6
two4eightfgrsix1five7six
one2three6hmktjgmbxhbjl
bqjtxhntjreightsevendgcfpmkld14four
22pgcslxtdhkhz8ninethreethreejcklznxq
146gqthree85twoseven
vlvxlmkfpcxsstsevencdbbdsix6
5sixseven48
llljbtxtrrpssrfthree9dpvrndphhn
qkhvq5
svrfthree8bdhjshcftdnine4
nfqnfkpchl5pcstz
pbx5mfkfour8
cptwonethreeptwo9gzmlkv9
gdpkprlzr6466fxlvfour
dlnpklqtfivesixfptrdh9four
onedpfskdd38qfvbjdnpglone
4sevenseven5qf
onepgmmhlgmtvone3c
247tsnmkd
threeznnnbtfive5tmdfxtwothree3ndjcszrb
oneeight6rhfiveone
threethreethreestvgljm4seven
bfbrk3vqsmone39hzzxdgp6eight
98rndphxhcbl
57fourlntgdngzpdsjkbbxnv7
twons4
three4threesixtwo2hf
lgqtcllzsevenhrrhqsmsnncmlz7
8fnngjrxtljncbvlt7ninehfzrthree
12mltqtqqrvjmjsbthnzpdjtwo1
lf1onevdmn2
3twozlqnddmrsixslphpvb
five4nrtcjmvj
cksgmkszrbsrgfkq7tqjxsshxxh7vngsnjt
mfiveone7
kkvtwone5sevenfcfnngpmjktrpxk7djgzmdthreehpp
fxbgtcjp4pxpjv5rbfllhfcpfive8
2gxrkjdtvxmnvs76
npvvleightlpd354vrfh1
zbnzdlzpqzsixnine4sevenqtddzzm6
6cxrhdvzbmthree98
77eightnine32four
56tshj77one
161xk26mspxkchfourfive
onenineljnsmvmfb7
6hxvmqtwo
khzninetwo3pbtsqxlqj3
1nine3
6xrzjskblfq
kbtsbckkonethreetwofour7lgcbxmjkjpnine
fivethreecrjznine7
8qxjdsspgn
vdoneightsix7h9
5bsjl
gfllsrrrmzbfqdrlnvtdzr3
7hs2
1onelseventwo
fivetwo3
2gnxv9sevenseven
gmmhshzvsixzssixseven6fivehbdvsjdz
75onegckzsnpnine1xfslhfour6
sixgjhnfvsjnrsixeightnine49
nineseven929rlsdkcpbjrjdlptjbzp2
9hbpplffngltfourpgk
twoldknnqxkkvvdjldqthree373
cceight9two
zmeightwohkgs6
6sevenfdmqkss4fivethreesevenfourqfnsvvsj
4two9njeightnine
two3gzseveneightfour88eight
2clqj
1ndvbhbtql
five4sixseven5ghlgbmdgfnqpfdm
34dt
9sevenrl5
dpbpqppsixngvmkflllcrtn8
sixgvsctkrp53sbsx
nine9qdrqfourjc
zvppfjpqch24
sg4txzzfoursevenninethreesqcdznksix
one3six2
5bgdjzxbrlsl3sixlbxtjdpgfkjmq2
drsldhgjtrvmmz3jgrp6
qkeightwosix95nine445cfnine
fivetwo82scjct7onegblrqrggv9
threepfgljkghzc9
8qvzljppstpnpeight9
8nine98tkxcfqtg
sevensix4two
2eightthree
cvhhmcgknj6threefive87
seveneight7dsvqnjsjsqlkeightwovzt
71oneseven9fiveeightnh
pdjtzxbgjxb3vzfdnjbthree
xqpfourmpddnfcc4one5
jnrms1fkssgpvvlrmf
jbnkeightone6zldns85sixnine
1fourbqfgxscdxmkvdpsrm
eightsixone8mjbrqkplkqntjdlfourpjdcs
lxdsp6prmfglvdfz9eight7sevensixone
qdksixvmsevent7xlvmtzdkhb
8spqjvhplpzldpthree2
zqfvpq7pz22
nnfbpfour1
one9ninetwovrclctwo
344six98seven
kqkj9qqvfxn
seven1fivethree
shlnhgq2c3oneseveneightwodv
eightthreepkcxlk7threeonethreetwo
pgmbhrzzqmvkjl57fourqk8gxjmbfqcjs9
eight5oneights
ninenineeightvkrsrkgh8
svnfdxxftkgzpjqxzkmbbcfourvfsg3two6twonezqn
mnvqcmkqvjlqvtltgdpktr3dkgkbt
sixmg29pplfzrsqhlvfeight
glzsbmzqjj43
1zqrnpqrcbzdsnfour67zpsxpkbbkmfgrzm
kxbbcjjksix7sixeightnine
zbnhsdxgt5sixsixeightlsdqrgseven
ninezxrllznbcvmldxfbvjx5fivedtpdhsjgk8
reightwo7
fiveninenftgfourjbdkxtxcb38two
sixlpck4sixzcppvhzkchmnpvqjjgffourj
34pmfdthreetwo
7zbnnkqone5three
qxkmhcllcqkk5qrqseven47
one62brghc8s
twotwo1xvgcxbthree
three83dfqgjlc8eight
twofive8l1twocqmcccgdm
two6cnine
h2sfnczjmslpmlvdlzrs
9nbblsvxbhdxqfour8
kfzcsg6sixonevrbchsjtfivenbz
mgtlqcdtwo7qxmszcqpjdplccxzccsixone5nine
mtfpphckdn1eightpdtkssqlxdhtlmcjxb
jbphmjjjrg2159eight
zqskrzb1pgfouronevxscgtlccl
lxvpgrck8fxhxm
fournrnlfive1xt
3nsjnb
eight1three513nine
39eightsevenfourbgd1mbljzone
6two88five2pjpp
fourjlftf7six1xsktfhcdff
3kcntzlmnnhhmtmlftggqljkrvninetwo
threesixonexlvxrblq24
xpgzzrcckdlnrczxndplbpcb452
ninembdnhhnhhzmvmtdjtkzmb9ncq9one
4fivebrqrxftwo
84tqzbcvgdrgpzpxjrone83zgzpsix
99five
mgsevenqgkckfhbfnine3
2seventhree
8fivethreefive2fivethreefive
scrtqsevenjmxlvjrrbdtqgrdghfivethree7seven
sevensix946onesevenfour
dzn14
nineqbxvponekvfffs96zjfhpthree
53twoqknxnxqbcone
fhonerone7fourpmxskkmmx7
eightlq4fourfourdbsdrvhhs17
nnpgtgcbvrggctbtkzht5xmkhtnnn7brpxjcxdrone
threecxnqkmrrpmzdljstdbfmk5seven
sdptbzqhn7sixnine8
five7jdzdzfqfivesevengtznjmqrblkkzkgvt
fclvllfthx47sixbhq16
9mbmsxbn
three82fiveseven4
p1kzmzpbhmcthree
eightj3681nmgt8
2two7
jdbjlvxhhzzsnmcqldrldeight6nine4
2threefive
foursevenrmfhjvzdv8bpbrvblll1onetwofive
65cfgkcrhbjk
6onecjxzxb72gmrblthx
4jlrvckc9
doneightghmjzzrfourfdzxflm51nineseven7xkxt
r5eightone
nkgknm1
rxbsnnzbrs4two4
1pqvmfh
r2vpdpkzqcs85
gtfzjnnhsknvjnmnine98
zrdgv4hnbpb
7one165vddbrzpmfhskvzxdh3
44szfpgjmvgt4eight
dbgttrbj6five85nsqmzscksvdxks
ffkrhbvq2
6fourbpjrdvlk
38eightsix
two4fivedzpgfbtm
3jbk46
slpkmrstrrvthreepl9bhctdfnfzc
nine4two5kkfmcjgxbqkttg
1zppnmcr5sjfsbbxh48one
8one8fourseven5
6three7nineeight6
96three
91twonelt
5sevenone2sixthreeknhzm
2fvhgcbvg7sevenzqdtftct7tvvtkpxbrq8
9nvr
eightfivetwodjg9
7four2six
5fkzgffzpgp3
75sevenfive
mxtwonexsdrpkqzgm1threejqftvl
onekxffhsqb4dkb9fivefvtcjsd
eightone1eighteight4
glfeight7eight4
eightcdfjjfiveeight4fivetwo
npxnp32kfsfjpggdtpkm5
seven7threevcb
xtcvsmdx2four2twonefl
mzkgnzstwo844dcseight
zgoneight65z1seven3
1two5lc
threevsrg5vdmfvpss27qzrmvmbz
7qbcnjg1nine1seven3seven
5fourxdxrhmxsj
v78
41nine
56fmktkxvltp4bztxkqfrdthreefive4
fourdnq1
ctlk31srscmmdn66sevendmjjqvgj
b7nine8ninejqqhhdnnpx5
ninedlxdshrzfmrnnq17
jbneightwofiversjctwogvvtprslpgfour9xcone
dpt5hbgbnmdlthree5sixvsvqtnmndpvv
sevenrztpgr8nine2
four9three1keightbmljrjgxkrbjdhbckbst
sfmztfjh49one
seven3three7eightwovs
48onetwosix4four4ntvrzvdztr
mphtwone5sevenfour7
4lgqnbdlone
hpnmtjbcdhfhsnxqhjhhfgteighteight8one5
cgtdtt22
plnbntcb8seven1gkgzngxq
7bonethreefour
ffoureight88
z2four3dfggfntxjbjhdr9three
5sixgpcm9two
sixpnlcgbcpdsixtwofour4foursix3
dstgjlh812
3xfhdfrgqjfourkfjone7four8
nine4three6sevenfourthreeeight
njvdqthkv5seven6fourtbklhrqqsixeight6
58rcfcjdkxcj3eightlndf
jhnt7zblnkdvhslrhpbkzdh
7threetrlkmmlzsmtwo4dmjf94nine
pssrhmtcv7twotwodjxvlbmnfive58
eightseven271onefxxrfglh
9bmtwo
three522
eightseventworckvnine2
6fourtzmlrkrztvtxfcdf5
3six4rdchffcg7
6threedqssixnine
fivetwo6
sixnpkr5xbxmjcxjznpnrf8brxtmgl
gtvg3zzbeightkphjs69pjprvhlgl
7kclrcjbtkcr63two5
threehpn38fivedcmfkgqqeightwovg
hbjcsfshsjqqfive9sevenfourthree3fzjpfjzd
five7qzhnthree1
nxhg17j8ninejnqlnine
ddlnxmcg2
rxeightwopqtpqncvd481154fiveb
sevenvbkzgzmnine4qhpptngvhh
bkmz6nineeight69
tqvzkqfourrj4one
sh15nzxzhqdxd96dqht
6rhztqrfninefsqszx
38cngonefourrhlljvpgz5vzhmbdjj5
8fiveddchtllpt
13rgzljhsdjfive2six
sevenbdnbheightfive7qbzjvlsr3
fivejmfvtnhz1eightkcdpjsghgtwonine9
five3bllpvnqgtzdhvprgb
t1vcttwo
9953three92
9hrgxdpf
sevennine579seven
threekp1onefrfjbrmmpmsdsvfour
4threefive61rrhrtdjs5
xbmvcfqp4onesevenmkgvsnprtl65
379
4966dlprfxmhmqdfour6
9lllhz8nmqxkzsevenxmbqvgqnj8
btkxnzonesevenjmkdcc1ff32eight
7three65one745
seven66ninelkbmvndm3
4three3sixeight3
177dcxqqbqpkxgctwo
48cjpd47sevenzgtkrdlfq
5ppzvklbtnine212qlmt
89tpmnr
dh1gfgb6threeqcm3
five83
fzlkpx6seven2twotjlqpgldlfour49
fiveninefivenxpzkrslxm44
pxxbnsfoursixeight5mjkeight
ninefivesevenz6
djddxtdeight2vqsvdgttxonefourfhdfxszvfd8pjcvzbgp
253seven71nine
eight6cb71
gfzptdj1nine5twosvtmpphj9mkhd
hfjcpvzeight7ggqrmkmzrhbthreeeight
chjonetwo2lqxfrbvq
fkfmntldvleight4threethreeeightfour
6j81threeseventwo3seven
seightwosvsnp4
8bqjk75254
6seventsmnfjtn4rthrp
9kksdvfzzpsszffouronenrqrcnine
5dkpp8fournine4one
4mpbzrfvj8two9sevenonenine
jzkdrrjsnpxzxbjhb5
three87oneonexppvhz3seven
two39kvrmz
vdfive8dntsz
mthreehzdghb3eightsevenfttmjtx
2dlnjsxlg
7threecrrdqksqsevensixnine
mdxhrrrfiveone6
8nine52
725two
jzpxt2vcninenjgfzddk
three29
zjrffvdgnpeight1sevenglpfkdb
two3qnhqvxeight
slqdvfmeightmlhjdcvbrmsjvrk6
two13kfxd28rzqhcdkjtbbstnhbfive
qhdqqnjbdk8eighttwoqpthree
one1brxnptch263eight18
threexntmdtwokdmhf3pdkgpthree
2three9jrcrmjvbrm
1mmlsfsdkzkvjtwo3b
sfkkkncf3three4seventwoqzhmcdfbhxfl
vfnqlnrzcrzm8fourvpzxqdqsfb
rjkhxqdmkrfive1
2four2nine9znp6seven
7doneone
9threetworqlk
eighttpclmtv4twofour5mtxxnpthreeoneightfp
onecfjgrbsjncmpqhqvhxtfpc21nineppone
eightlvjxmxhfive9fb
ninenrjlmcthree3four
eightlxrnvljrhksxzfxzbrt644
blmjnfgsrcms31
zsix16xtjvrtn
lkjfpxjh1sg57xbxlsxcrsix1
mkmdcsbgvd19ninecsevenone9
three1p
4prbjfour1ninesix
pkf7sixsb
fiveclczl8sltncdxsix
4ninefourseven5three
3lpchjfgbhzjbqggsfoursixseven
twonmcfztwoqp1one5
87sevendbqdsevenrlzkrhoneeightone
4three4
2tthbbcpcr36tqvfjkfs
rtzqgsixnine4bzqhjrq8five
eight3fivel4kd
1fourfourdpvvghvlzxtwothree
2gzgzmrpbztm1cgzvvrtqt
qdrfrkncnt1eight
smpz4mjrbthree1ninefour
6seventhree7
5278nlnfjrqlone8
nine6eight
twoqrtdsreight2dxhknhpdzz2pdbsnlpljlxkv9eightwop
vl6eight9eightsix1lmth
qxsevenkckxvmjkb1
hvhlm2twotwoone
grtlftfthtthree331dzzzoneseven
ftpkgvrsc85cplddgnn
lhcbzhgfjlgmcsn66
twofourqjrltlvcmmzv8nine3ps
knvz5four
threefivefoureight7four
one9fournine5
cr8twofive7two4tlbpgbngsp
seven6gzn8trjmzrvjthtpzld5dcqnjmtl4
1five1fourfour8hqjzshnszhzgcchs2
tld78blgffjksevenninekmjnrzlvxr
15six
fqlmqhjslgnllgs6three
jvhmkfjzd5
kfkxjmbrgjzmjtwo2qnh45pxrkrcpmjd
dh119mvxlfdft6sevenqdxb
five5jl
jhqp2qzkfvjdfts
teightwo6
kscczzhftqxmbrjtm46five5hncmntrgqz
65341
cxjpgfourfour98spklghbv73
5sevengnxldmlvnrzsthree
bsn6three
fivesixz36
snpmdmx65five4
vrjbjsixfour37onesscclpgxr
four58
nlrflkxjzbpckmprkp435
pp78
rhz3dblsmbl8mvmthree3svhbzb
qtztdn4
rqrdr5ninefivexj
fiveone4ninesplghqrbcnmmzll4mvklfmxv
tj3ninekvczg3cczmhkdf
fivelnrjmn3jvlmhplj4fourkvdceight
five6jrbkxrvmtqfvbq7pmjmdkmkv
6gcllrgktc
21zffhnksmjj1rcdpkcrznine
threegpnpftrstmfive4ninefive
1cnsixpnvrxq
33qghllh
gfour1rmznkmplqfsevennksglsfdqtwotwonet
kjqcslq3nineoneightkq
three8oneninepjpqnzccthree
3jpfhtrfrffournmrxrpdjm
2svsmkptdcmpkhbpmnineczjsjvlfpkckxscfour
dqxpjsnineonenvhptwocprtsbvcl6
ninethreesixninesixxjjjkvtbgd5hrstthree
fivefznprpxccnk8four3twobllsjnfjnx
ghh2skv6
eightninethree4jbcgrqeightfive
4ltjfxjbthreesixbrtzlbfdx7eight
one9mvbrcc
three5khjdjlpl4hrzqblshkxdgseven8
czvxncr2
eightthreedhkkpr9hqqdtwo1
fouronetnhxgzstdx31six
fzcnineghrfbcbhhv263
eightfive525onexgrzfive
zclvsg9three6eightkeighttwo
two35bhmnlt
9s
xbshssrspj643four4vrjdnlf
nineseven4ztnchhpx92
gpfive25dvx
1five5
jtcstj1kqrsbhsdsk8
eight1sixsixbnsfouronecsv
tgplkg2hgeight
seveneightxsgcsfxd9dpkvltrsnlkccrskrbppmkxmpt
pddbvnrqzznqmmonesixjgpgm9rmlxvqqmdbcqhng
xqdbsktwothxzfsmqptrjbthreesixhqgrffqcnine2
vxvtfjsevenfivethreethreensflthreenine6
8one9sxrczdfg2
mdtdgjthreethreefjzqs16
dnvpjteight6tcn
eightnine3cdzk
three6tzmqbzgmdeight9fiveztwo2
1pg6three6sevenmghhdx3
41threenine1
ncbrrm7six2nine
eightxrxdsnsvzddhrqvkfmseven3ntpqhsbttqmlp
one3fourmmrhpjmnjnqxkf4ftkxsvsix
54zdqrrmzd
rftdstx6hdrhmqjs9b35
fiveseven8five5
gzfkkqznk4
six32ckkbh
jpk171fivesevenhbjlddveightvcqh
71foursix7
2nine4onelkvplzhj7onen
4nrvsh3twoprvpgltsix1pzbdhvfour
skvnpjfmkhmf7547tszjzmq
two3ctthreesix3sjflprlrcg8bnhxs
six41nmgpztwo
dkkhmgmjtwo4seven
8jqcmhrbmcninehbpxdjpvbk882ktjxp
hlbhcfivetwo77three
19ccmrmh1lgmngl8
5eightnhfsjzjseventhreefiveeighteight
3dkvxfive
39zn
cqsklnrdhtwoeight814rdncfthrjbc7
13pfspbxkfrr
five7sevenfourfour42tppvrfqrvz
nboneightfour9eight7threefournmvcrgmkx7hs
sixoneppbone5ctdhjzzrnine
5one1nineeight1two99
7rjvqdvgxxmb
sixbkdpftd6
699
rflvscthxb9one9jtfqd
sixtwo2hsclsg
8nzgpt7eightfour6zhjxqjmrq53
pddnfour1one749five
gbbprvrq23seven77zssgktwo
ppc2flmdjrp7twoonerjhtlv
vlltgb8seveneight5clqsthree6
onetwobtgdkkxv1five6lcfvkhm9three
two8threesixsix
5threeeight
onespeight9twonexpr
pzfourseven8five9five
9mbdbphhrfeight
fournt14
jlcb2
three3eightnfj2
6four2fgsndlslkr
8sevensxcjl6fivefivesssevenqghn
3xgzj66svlvqgcvzzlb5vvjvmvb
hfhmngkv64
263six
oneone15qtgtksjdgz27hjl9
1six8mrrkx6
vdhslzfgsevenfivemseven4fourq
vdjlnrvqjb5thsevenfiveonexskqrfourhtcl
1ninefoursix6seven
twobvpnjmspxmgxkv4twotjjkbxfgs4
fivefour8kfjrpdddhr
dvbljqpznfjjp6sevengvbdxm1bnzhqmcdgpzrd1
34cmclpfrgrjpxpjpdv
9xcdkhtsdmtvlrtlcbsixtrjqxg
rdhqfcseventhree9tqggvslm
gddfoursevenrqvqdkqbr6ninecfd
19cjpltfxspkpxkctdhjkncnbtwoone
mseven7six4five19hjd
jcqlxpmmj7rsrrngnq2seven
fssvonepgqmrgbv1
fourfivefoursixthreegjgxhbzrgx1
fzdfbxsths5szkgkgzdmnvtnine9nqb
rszfmx6eightnine6eightj
three199twol7six3
threedmxbsevenjmdvrzlfive26
ninehkbdkc594one
6fsix
fiveseven51fjlx6
bj3fivesix1
9vxcstvpf
threeseven6
ninefive4twoonetwo
8seven6
twopxsts95
pzhxsxnnkthree6
6ninem591
7drfqdsjsnfdbqp
p2plgbninethreesixthree
5seventnhvjfrlkggfjbmlzxhnnnsh
seven691rjmnfzddfive
466mmgnh
dsctthree7
eightqlfourzndmzltp34xjbdmpjs1
stmprbctwo1ninesxbzqkkdqgdqhone
sevensixeight91sixjscqxlzj4
vrctfpbp2bdknhtwothree68ckzlgkghponeightg
one82lzh1m9
5dgkljnlvnthbdfeight7x9kloneightrp
9pdsgfourzcfour51one
3twonltnmdqttqmj6fivefivesix
1four8szcp
dqbrkz8xp
3twoftqxxgdg
sflsixzjq1
6nine4plhvcthree
znzdrj4526fjtszspfour9pk
785bttrfnrtzvninehqzthfr
6slx7six8qhppjmbcrj
dc1fdzzsvnb
1xfourtwo
1mvt283
fivecflqkthreek2
7gtdlvbcxvrxvx1264eight
b1pbpcsix
fivefourzqrmbpgnqzgpzhfour9
seventhree5cnvjmthree
ksblnqbkgnfone8
8fourvkhkhlsjq
twoqjvb9zjvpfz2onesixk
tdjmrcvsevensix1seveneight
5mqxkkcttfivezdbcdmlfivebchqlcktpseven
7bzsnljccxqbtq3dsfsbrlpgqjzxeight
one4mjgfhzsjdc3crgthhjrznd
sixthreektx8xjbdjlq
bgvldztfivetwo3tldvpzkmonepdvppd8
xhzm91
oneskfoureight23
ppsj64
fgrf45four5
four2two
798jcgzf
93mxc
hn5q9rm2vtksix9
cvdldzschflc9eight
mqdtndzvv9eightkzxlgvcggrkltzlphcfq
beightwoone6rqjcqq7sixfourrkghseven
two9nine9foursevenfourone
793sixzngtcbrbtjmbjbqxzpqjkn
1five4
x4
qmlvsptmqhfvbfiveonemnmzshczjgsvgsgpps2seven
28eightmt9
1four8hbpnktmn
6nine5threeplcsdrn96four3
two3vdvh6
three7fiverkjmllclbprmqgb4
strvnllfmqm2threesixbbrjxbjvvxvjhr91
4dbqqbdcddldhgxjrnfivehz9
v81nnvzv
3ssptnqhmrzbseveneightwoxdx
ninetthree72
556five2three
99175onepzqpj
8tmbfpbsninenineglvbh7gnrd
three8zmtvxqmksfpvkfourp9twooneightzgs
khmcnch1tb2
5jtcggvdfzmsppshsqlhz
249sixtwofoureight
6sixfourxpnt
6threen
16qmgfjjmqnktnxgz
kjvbk1zzqhdkgxxghxsix77
sndnsm1741gtszjkmvxsevennine
kzlrrrjbtrrgf5jsbgxn
4bnfdfbgthc
ztghxzrdprvlfx4
54tzqlfjfnthree7twonine
knqkbts5ffqhdsfpmdeightnfhpjcmfmrl
jzphxleightjhxvhdrseven5mktwo
1fivegxdknn
3onetpqdddbhb2seven2
7seventhreebzcqpkdck5dc
seven71
eighttwo1
dfflcrcqfiveone2seven6
qpf8
1seven1bjsmxzr54four5
mmztpseven836four
three8htqhzkrxhrfourthreebdrmjsvpfb1
zjhljpmmdms998ffjqgxgbkdbvxxppdltbrpzcbf
rj76
vlxnnqxr4eight
2twoonefourtwosevenpdpfkgdhhzlbfq
cn5five4
hhqzsxlm45twonine
ncszsls1z2
ktxkkbhcxcbnffzbsix8bf4r
xrk1bvvrxvkreighttwoeight
4eightqbmtpsl2six
13jzchfndpjpone
fivefvm1sevenhjxrnkg2bfmdnbj8
9gcfmkhqrczseven7jkshrh
twornrbjlvsdr1threem
njzxgftwo4two
threertvjcmtlr7nxhghbnvkq
6seven8drfivecvv3cr
fivefiveddnhmz25nine7
2eight4nine47
nthreetwotwo67
4mbsqbpvf7threedp4fourvvdgkvzfkz
sixthree1twozxldcqnvdcksfiveseven
nh2
4rczvpnvsptwo
foursevenone4
5sixkvsjjqzs32kbghctffhssmg98
zbclnpj3ncgjrpz6tcgdvjpdmkk1
4mmsbtfivetwodgvbhrzrlh
sfour2fivefour
jx9
xltwone6
1szbglvbcblqtdqrbdqqhbfzrrq4sixfivezfb
two8klhvgbm97eightfour6
szslrmeightsz61eighthnhxv1
seven42sznlgdsdgfj
three2eight26nine
4sevenfmxrhgkll3fivefjsxninefour
onenlzksgpz2ggnvsmlxxsvqsix6twodbdzvkbhhl
mxs62zfsmkgmxzl6nbsix
9sevenlbbt9twonet
32tworbbmnbgqjd
jrx7mpbcvqfhfr7sixsixkqmcj
vcdcthreefour5bbqbhxqjkqqzkdk
onesevenfourhxfvfoursrq4n1
six1hglqzjxfxxd4
4lkjljr2
3brlmgbpdnpslgcsevenxrrftvzlxc5nine
czhmhhnrxrbzgtmvn1mbjpnrlsevenbfbztqfp
xnctftbbm3seveneight9kpkfour
xsixsixh5three
2lzslqtllcpfdq3
six2xstqpjctsxgtwo941
sixthree6
636bmxvvfztzvbrxbfh1four
h4pcllfnine
pknldv4jzlkngvqslchstsnfj7sixsevensevenb
78threegfclmstbxt
9three9mnkhrrlbgonethreef1six
2bzslfdpvoneeightthreeone3jpqjk2
1nfjttgvlsn6eight2hznlx
7three7sevenbdngj6threeseven
one8qdldvhjhrfsixgscfnpvbm2
mqlhrjksdtbrvrgvpc884ngrddthzjgtjxnjq
kpndxtponenine97
vcpsjrxlpfqprxz7threeeight2
ztgckbfr5twodpznplgl32rfqjhrvhgmkd
threehfvxnkfhdgmtwo16
6seven36qrndxhxljgkskgtjdtscczz1
ndcqhqnvtppkfbgljkrd3xfdvqhxkx94
sixgonebbblkbbqvccnvm2jxpvlnrbmjnsrnqgbb
sevenmeight2rlvbkxmxfour3nine8
72bcdrcbzffkc3oneightnc
onesevenfour57
sixnmplqnfx1eightfour
nine5bh4eightseven
7gcqthhztknjslxfbcj19
five2fivethreedbrkgchn8
221565
26three83vmtlj
nine5threeninezgjcpssevenone
xshxdblgf8threehxkntrdn6lghpzt9
fdfqxmn3eight6zssbgnddcv
eight1hrrqmzjjq
ckhpqtwodqz9r
zbtjjffourninesix7bqz
jeightvfdqttxmsix3pnh5lgf
6onerqbdslg
fivep26two
onetwogscnvv1
eight1487
threekksixnine6
69sixoneqhhrqsg
8sevenmtcm
three1jlbfpfdxpqpzspgcllfv
27one
mlmfourseven3
sixninercgrkdpd9
three7kmvsztmxjsixthree3bvhmhthreesdrbq
mk7
jstwone8fourkrlmtfk7qjvrghcbfive5
eightchvxseven9nine252
five68qoneeight
seveneightfive2threetckdpkqrnqpthreevlqlq
jlcpvfxpq867xfcjcddvjkjdfnqtnjp1
prxzmdmbxbfljckhd7pbc5three
92fninenhnxjlzninenqmhmmlsfqf
ftmljhqxjdhddkbtxlbz9five
oneone4cfsnxltqfbnzzljseven
gbktqjbrtgsevenninexf6seventwonine
nine3fournine
1fourtwo17seven
twosixmpnpdzmjxlmjsjdnkmnhmdtdg7xrbknkplsix6
fqzqxzkgzrlkmqfour53seven2
8three27eight4onekgrlc
nzrlgrvfive3vdtqcqrm6
4qfgvppninetwoone4
threelpmgkfxoneseven8threebmjtdl4
slkrnqlpfxcrgxmnd9one7
fouronekbfmdrjxvn8kz1twosevenrth
8xbvkfqmzjg
six4xcx97lzninefivenine
threesixhfvnjrvpf1sixseveneightfive
8ninethreethreepdlddhdfp
two47threefour
61czbc4cvklssbpt
8712st2
lgflr1three
ninenine9
ptx86five
291
seven82three9
mxfhmtcjpxqqsl68hpcfvjj
1qmvjl6four
3six84
fivefivefourfiveeight7eightwods
2seven5five2d
6five3hqbjsrzhtkv7
4pgcsxslr6h623
three8one
8xnsgbjhz9
3gtqbhdzjninetwo6thrfssxqptjbtmkkmlddhdm
6mldxcjnxv9
mc8one88
h4
fivefour2s
9q
fiveseven8two8lkrtvdqchbvnmzmlhgqbcx
cpcgpg9cnbsixsix
vglgjf9qzcspsnsbrktbnpnppzmgldtwo
three2kllpcvmmgcseven
twofivekvcnfpkplnv2threefivehvxpthree
36h6hzfhhbtqgqfgthreesix
86kkqqseven9
7jgncdninejvmqdmone5sqjp
seventhree4eight9three
38knvgxrftdtwo1
ksgjdlpxhztnlfc6hxncbgbmgzldvlvmgrjkmtwosix
dtoneightone427
3518msltkgqthvjhs
75qmqvkmf5six
85pvvthreeseven
ckqoneight4xqqnkdnzpggmxrmhkhx171nineqgd
76nine
6threentcfsvvfseven1eight
23fivetlqdfhxgg312nine
1pjninexlqnthreeoneoneoneightx
6one1
lfjmdhpj9fiveonelvfivenine
4xjmxfxxfsbxgk9sixhq
8six8vszdsslqntzjqxx8two
4one88995five
nskmvtzd1five
4tpzxkbfq1vlxpvzbxnhqtshzhfourtwo
7six341gsl77eight
94dvc
qzkmxqqh6sixvn
sevenoneone26m8
ninedtfvl3four15eightjtpfhlzzct9
5ninesixtvvpblfqgb
5twoseven4onedcsqj6
gcbtzns6eight52ccvvpsbdgfgg
tczvbfxfvcbfxzvvxp9three
fourkczgcb9knnpkd3
9ninefive8nine5
tnkpsxjm7four45bpdg
twofour1glkvvkq
fiveeightfive8vbqtsmhjqr5vgbmsxrkh7four
lhncnxngfour9five
5six36five4
74ckc
lkeightwol11
five3mnlbmsg2
hmvmgnqlhdgonedxmjxxbpbpt1four6zh
svmmzbbj5
3l4
1dgfnone17
667
1qlcg4seven
756ghcxrmrgtninefivegvmtjhfrj
6twonehjv
vpgeight7twonlcfcdspmvpcfj6five2
76jhj4sixeight
vcfrscgccnine6j
mhm8kfjl33ninefivethree
85bfqjpdmbg92rjbthcrfgvcjzqhpgb
nine554ltcgj5
eightfivevjszdqkf96ninemgldmdth1nine
sixq4pcczfqc
three7two
eight9csevenfour
fpnnxvcttscfrgponefive8twohgfkhbssix
smkjtzm72vmtleightjmkxkgpfzmjpmbbd
7two1lxvqqzzrvj31fourprzmsdzbf
kffeightwogzcqpzdbhfvmckxmbhrgvonevcshkbctbc524
fcsoneightnmtgzbbnflnnlk5two
6gkxbhgzbmltwosrmone1five
sjrfkfjszsix49
47seven82fourfhhfmlshdsix
sixhjqszlc6kzzbqsldk
nineptfourrvrghq7
4four5one148jcbvnbj6
ninezfcrvxfnjd6nine
bfjfbpfseven7
bdzmktwo4fourkrjj3
nhnfv725
sixeightsixkkndrj7
eightone2m246
fourhsbdblm74fourcphtwo
khrjxxrltbpngsmzndgsjmzvgqxfhvkct6eightzrvpmpcc3
mlsc4qnlhqjfour
956onefive
1xtmbmkscvv2onefivelvlztjtctz5eightnine
32tmphhjnlfsixskninekjdcftm
krgp1hdhgvgpgbvbxgfngvktwo
klpnpcplfhlztjgv17six
5dsxngmpdvjhnlbhxmp7xqqtgdoneightdvm
threeeight3t
5fourlpqzq2two1oneqlq
eightfivesixnineeightfour1
52ppqztdggqgtphdfz4fkrkfztzzqldpg
seven7zbone
xg79onef6eighteight
three6nvhnqeightgkttsvtwofive9
rjsevenonefllcrdnbmqcmxqsq38
twoseven2dppxgmlhr61onenine4
sevenb3btzhscqbrxxjkhtzpv
859two
threetllmjmhdjr18two
94tmvfpzmgvzsm1fmbhkhzffkzllx
nlkndffq2three
6ninemfour8rbxvj6
7clxtxrt1lqg5
onefivegjnfzkvg9
fplrvmqjdfxvjdfivesix6rpnkfour
5fivetwoxdgchbjjrckntlgblsxlcznfd
eight7fourfour
3kmtjlfbgssixmspkfzrgxtctksix4onetwones
8five15sixthreeljsfive
9lnn
nine7pzrxnnkthreesdjxphsrf4hc8
three6115nqhllcmpmzcfour
vpqjnvmltx8fivefive
vrlqlj5fivesixninebqhgcpgmgkmflvn
76eighteight7
8qkmnsjxbfhcpsvn4
oneninexqdseven4threefive
rpcvmnng162fivesixseven
31628eightthree
83d6fsfqdghztwo7bmvrlh
gknfcdqlrs25
9gkkth2ps
bxnvsjxqleight9ninenine
rsmcrqlnhsmjhspseven96vsckknrggbjd4tgtgbkxgvt
8seveneightcxrh
tkmfour8fivevl9one
8mgrxk
fourbgckqkeight6f
threetwo3eight652pp
ninefivetwojbhglxfxzfctwo8
jmjtcvpsxzdbkbqthree1qmgznpbzlthree4six1
foursix5
6nbdzdlmqpdlgpcclc
24
pseven3threeeightseven
7nine7gjdksbtqrrdsr
5ppflb48tkcffone8six
five2two7hstbbqzrninegbtwo2
eightfblzpmhs4
fbbdeightzzsdffh8jbjzxkclj
3nine6five1
```



