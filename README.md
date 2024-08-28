1. Εισαγωγή
   
Στο πλαίσιο της εργασίας καλείστε να σχεδιάσετε και να υλοποιήσετε σε Java μια απλοποιημένη
πλατφόρμα συνδρομητικής τηλεόρασης, όπως για παράδειγμα είναι τα NetFlix, Disney+, κτλ. Μέσα
από την πλατφόρμα, οι διαχειριστές θα μπορούν να εισάγουν περιεχόμενο, π.χ. ταινίες, σειρές. Οι
χρήστες θα μπορούν να έχουν συνδρομή/λογαριασμό ώστε να αποκτήσουν πρόσβαση στο υλικό της
πλατφόρμας, να το αναζητήσουν και να το αξιολογήσουν. Στόχος της εργασίας είναι να εξοικειωθείτε:
  1. Mε τις διαδικασίες σχεδιασμού, υλοποίησης και ελέγχου αντικειμενοστρεφούς λογισμικού
  χρησιμοποιώντας τεχνικές απόκρυψης πληροφορίας, πολυμορφισμού και επαναχρησιμο-
  ποίησης κώδικα

  2. Mε τον συνεργατικό προγραμματισμό, δουλεύοντας σε ομάδες και σε αποθετήρια git.

     
2. Γενικές Προδιαγραφές του Λογισμικού
   
Η (desktop) εφαρμογή που θα αναπτύξετε θα πρέπει να υποστηρίζει 2 γενικούς τύπους χρηστών:
διαχειριστές και συνδρομητές της πλατφόρμας. Οι διαχειριστές θα μπορούν να καταχωρούν περιε-
χόμενο στην πλατφόρμα (σειρές και ταινίες) που θα γίνεται διαθέσιμο στους συνδρομητές. Για πα-
ράδειγμα, τίτλους και περιγραφές για ταινίες, διάρκεια, ηθοποιούς, άλλες σχετικές ταινίες, κτλ. Οι
συνδρομητές θα πρέπει να είναι εγγεγραμμένοι ώστε να μπορούν να αναζητούν, να αξιολογούν και
να φτιάχνουν λίστες με αγαπημένο περιεχόμενο.
Θα χρειαστεί να υλοποιήσετε κάποιον μηχανισμό σύνδεσης (login/authentication) στην εφαρμογή
ώστε να εμφανίζονται οι κατάλληλες επιλογές/καρτέλες ανάλογα με τον ρόλο του χρήστη
(authorization).
Το πρόγραμμα θα πρέπει να αρχικοποιείται (μέσω αρχείων) με ικανοποιητικό αριθμό χρηστών και
να έχει ικανοποιητικό αριθμό καταχωρημένων ταινιών και σειρών με τις απαραίτητες πληροφορίες.
Η χρήση αρχείων είναι απαραίτητη για την αποθήκευση των ρόλων και των credentials των χρηστών,
των πληροφοριών των ταινιών/σειρών, των αξιολογήσεων και γενικά όλων των δεδομένων και αλ-
λαγών που γίνονται κατά τη διάρκεια χρήσης του προγράμματος, για να είναι διαθέσιμα και μετά τον
τερματισμό του. Για παράδειγμα, αν προστεθεί ή διαγραφεί μια ταινία και τερματιστεί το πρό-
γραμμα, την επόμενη φορά που θα τρέξει ξανά το πρόγραμμα, θα πρέπει να είναι στην ίδια κατά-
σταση ακριβώς πριν τον τελευταίο τερματισμό.
Για κάθε κλάση που θα αναπτύξετε θα πρέπει να υλοποιήσετε και αντίστοιχη κλάση ελέγχου χρησι-
μοποιώντας το πλαίσιο ελέγχου JUnit (εκτός από τις κλάσεις που αφορούν τη διασύνδεση με τον
χρήστη (GUI)).
Το λογισμικό σας θα πρέπει να είναι σχεδιασμένο βάσει των αρχών του αντικειμενοστρεφούς προ-
γραμματισμού που παρουσιάζονται στο μάθημα, ώστε να είναι εύκολη η συντήρηση και η επέκτασή
του. Για παράδειγμα στο μέλλον μπορεί να θέλουμε να προστεθούν και άλλοι τύποι περιεχομένου
(π.χ. μουσική), χρηστών, κτλ. Οφείλετε να διαχωρίσετε την λογική της εφαρμογής από τη διασύνδεση
με τον χρήστη (user interface - GUI), έτσι ώστε αν κάποιος θελήσει να αλλάξει τη διασύνδεση, αυτό
να μην απαιτεί αλλαγές στη λογική. Επομένως, θα πρέπει να αναλύσετε το λογισμικό σας σε έναν
ικανό αριθμό κλάσεων, και όχι να βάλετε όλο τον κώδικα σε μια κλάση ή σε έναν μικρό αριθμό κλά-
σεων.
Σημαντικό: Οι κλάσεις της διασύνδεσης (JFrames, κτλ.) θα πρέπει να είναι σε διαφορετικό Java
package από τις κλάσεις που υλοποιούν την λογική. Χρησιμοποιήστε για ονόματα πακέτων τα “gui”
και “api” για τις κλάσεις διασύνδεσης και λογικής, αντίστοιχα (ο σκελετός του repository που θα πά-
ρετε ήδη περιέχει αυτά τα root packages). Η ύπαρξη κώδικα λογικής σε κλάσεις διασύνδεσης θα με-
τρήσει αρνητικά στην αξιολόγηση (δείτε το Παράρτημα Βασικών Λειτουργιών για περισσότερες λε-
πτομέρειες).

3. Σε αυτήν την ενότητα παρουσιάζονται λεπτομερώς βασικά χαρακτηριστικά και λειτουργίες που θα
πρέπει να υποστηρίζονται από το πρόγραμμα. Ο αριθμός και η ορθότητα των λειτουργιών που θα
υλοποιηθούν συνεκτιμάται στην αξιολόγηση (όπως αναφέρεται στην ενότητα Αξιολόγηση). Οποια-
δήποτε πρόσθετη λειτουργία υλοποιηθεί που δεν περιλαμβάνεται στο παράρτημα είναι προαιρετική
και θα μετρήσει θετικά.

Α. Καταχώρηση ταινίας
[Α1] Τίτλος: π.χ. Barbie, Oppenheimer.
[Α2] Περιγραφή: ελεύθερο κείμενο που περιγράφει την ταινία.
[Α3] Καταλληλότητα: Αν η ταινία είναι κατάλληλη για ανηλίκους (<18) ή όχι
[Α4] Έτος πρώτης προβολής: π.χ. 2022
[Α5] Διάρκεια ταινίας: σε λεπτά, π.χ. 89 λεπτά
[Α6] Κατηγορία ταινίας: προκαθορισμένη λίστα τιμών [τρόμου, δράμα, επιστημονικής φαντασίας,
κωμωδία, δράσης]
[Α7] Πρωταγωνιστές: πεδίο εισαγωγής των ονομάτων των ηθοποιών (απλό κείμενο), π.χ. Cillian
Murphy, Emily Blunt, Matt Damon
[Α8] Άλλες σχετικές ταινίες: θα πρέπει να μπορεί ο διαχειριστής να συσχετίσει μια ταινία με άλλες
ταινίες που θεωρεί σχετικές (οι ταινίες πρέπει να είναι ήδη καταχωρημένες στο σύστημα για
να μπορεί να τις επιλέξει από μια λίστα).

Σημειώσεις:
● Όλα τα χαρακτηριστικά είναι υποχρεωτικά (* required) εκτός από το [Α8] για το οποίο κά-
ποιος μπορεί να μην ορίσει τίποτα.
● Θα πρέπει να υπάρχουν κατάλληλα μηνύματα στο GUI σε περίπτωση που υπάρχει κάποιο
σφάλμα στην εισαγωγή, π.χ. κάποια υποχρεωτικά πεδία δεν έχουν τιμή ή γίνεται κάποιος
άλλος έλεγχος που αποτυγχάνει και δεν μπορεί να γίνει η καταχώρηση.

Β. Καταχώρηση σειράς
[B1] Τίτλος: π.χ. The Big Bang Theory.
[B2] Περιγραφή: ελεύθερο κείμενο που περιγράφει την σειρά.
[B3] Καταλληλότητα: Αν η σειρά είναι κατάλληλη για ανηλίκους (<18) ή όχι
[B4] Κατηγορία σειράς: προκαθορισμένη λίστα τιμών [τρόμου, δράμα, επιστημονικής φαντασίας,
κωμωδία, δράσης]
[B5] Πρωταγωνιστές: πεδίο εισαγωγής των ονομάτων των βασικών ηθοποιών (απλό κείμενο), π.χ.
Johnny Galecki, Jim Parsons, Kaley Cuoco
[B6] Σεζόν (season): λίστα με τις σεζόν της σειράς. Κάθε σεζόν πρέπει να έχει:
a. έναν αριθμό (π.χ. σεζόν 1)
b. έτος προβολής
c. μια λίστα με τα επεισόδιά της. Για κάθε επεισόδιο, πρέπει να καταχωρείται:
i. Διάρκεια επεισοδίου: σε λεπτά, π.χ. 89 λεπτά
[B7] Άλλες σχετικές σειρές: θα πρέπει να μπορεί ο διαχειριστής να συσχετίσει μια σειρά με άλλες
σειρές που θεωρεί σχετικές (οι σειρές πρέπει να είναι ήδη καταχωρημένες στο σύστημα για
να μπορεί να τις επιλέξει από μια λίστα).

Σημειώσεις:
● Όλα τα χαρακτηριστικά είναι υποχρεωτικά (* required) εκτός από το [B7] για το οποίο κά-
ποιος μπορεί να μην ορίσει τίποτα.
● Θα πρέπει να υπάρχουν κατάλληλα μηνύματα στο GUI σε περίπτωση που υπάρχει κάποιο
σφάλμα στην εισαγωγή, π.χ. κάποια υποχρεωτικά πεδία δεν έχουν τιμή ή γίνεται κάποιος
άλλος έλεγχος που αποτυγχάνει και δεν μπορεί να γίνει η καταχώρηση.
● Υπάρχουν αρκετά κοινά χαρακτηριστικά μεταξύ ταινιών και σειρών. Θα πρέπει το πρόγραμμά
σας να κάνει σωστή χρήση της κληρονομικότητας (και άλλων αρχών της αντικειμενοστρέ-
φειας, όπως αφαίρεση), ώστε να υπάρξει όσο το δυνατόν περισσότερη επαναχρησιμοποίηση
κώδικα (η ποιότητα του κώδικά σας αποτελεί κριτήριο αξιολόγησης).

Γ. Προβολή ταινίας / σειράς
Για κάθε ταινία και σειρά θα πρέπει να υπάρχει η δυνατότητα προβολής των χαρακτηριστικών τους,
καθώς και να υπάρχουν διάφορες επιλογές ανάλογα με το ποιος τύπος χρήστη «βλέπει» την κατα-
χώρηση:
[Γ1] Για κάθε ταινία/σειρά θα πρέπει να εμφανίζονται όλες οι πληροφορίες που περιγράφονται
στις ενότητες Α. Καταχώρηση ταινίας και Β. Καταχώρηση σειράς μαζί με τον αριθμό των
αξιολογήσεων που έχει δεχθεί, τον μέσο όρο των αξιολογήσεων της σειράς/ταινίας και να
φαίνονται όλες οι αξιολογήσεις (π.χ. σε μια λίστα), όπως κείμενο, βαθμολογία, ημερομηνία,
μικρό όνομα του χρήστη που έκανε την κάθε αξιολόγηση. Επιπλέον:
a. Αν ο χρήστης είναι διαχειριστής, θα πρέπει να υπάρχουν επιλογές διαγραφής και ε-
πεξεργασίας της καταχώρησης.
b. Αν ο χρήστης είναι συνδρομητής, θα πρέπει να υπάρχει επιλογή καταχώρησης αξιο-
λόγησης και προσθήκης της ταινίας/σειράς στα αγαπημένα (ΣΤ. Λίστα αγαπημένων).

Δ. Αναζήτηση ταινίας/σειράς
Το πρόγραμμα θα πρέπει να προσφέρει τη δυνατότητα αναζήτησης ταινιών και σειρών, και στους
διαχειριστές και στους χρήστες, με τα εξής κριτήρια:
[Δ1] Τίτλος
[Δ2] Τύπος (ταινία ή σειρά)
[Δ3] Όνομα πρωταγωνιστή
[Δ4] Καταλληλόλητα (για ανήλικους ή όχι)
[Δ5] Κατηγορία μέσω μιας προκαθορισμένης λίστας τιμών (τρόμου, κωμωδία, κτλ.)
[Δ6] Ελάχιστος βαθμός συνολικής αξιολόγησης (π.χ. >= 4)
Τα αποτελέσματα που ταιριάζουν στα κριτήρια αναζήτησης θα πρέπει να εμφανίζονται π.χ. σε μια
λίστα με μόνο κάποιες βασικές πληροφορίες, π.χ. τίτλος και τύπος. Επιλέγοντας ένα αποτέλεσμα θα
πρέπει να εμφανίζεται η καρτέλα της Προβολής (Γ. Προβολή ταινίας / σειράς).

Σημειώσεις
• Ως κριτήρια αναζήτησης θα μπορούν να χρησιμοποιηθούν και πάνω από ένα ταυτόχρονα,
π.χ. και τύπος και κατηγορία, οπότε τα αποτελέσματα θα πρέπει να τα ικανοποιούν όλα τα
κριτήρια για τα οποία ορίζεται κάποια τιμή (λογικό AND).
• Αν δεν προσδιοριστεί κάποιο κριτήριο, θα πρέπει να επιστρέφονται όλες οι ταινίες και σειρές

Ε. Καταχώρηση αξιολόγησης
Αφού επιλέξει ένας συνδρομητής μια ταινία/σειρά (π.χ. μετά από μια αναζήτηση), θα μπορεί να την
αξιολογήσει, καταχωρώντας:
[Ε1] Το κείμενο της αξιολόγησης (ελεύθερο κείμενο μέχρι 500 χαρακτήρες με κενά).
[Ε2] Βαθμολογία (1 μέχρι 5).

Σημειώσεις
• Όλα τα πεδία είναι υποχρεωτικά.
• Θα πρέπει να υπάρχουν κατάλληλα μηνύματα στο GUI σε περίπτωση που υπάρχει κάποιο
σφάλμα, π.χ. κάποια υποχρεωτικά πεδία δεν έχουν τιμή.

ΣΤ. Λίστα αγαπημένων
Οι συνδρομητές θα πρέπει να έχουν την δυνατότητα προσθήκης ταινιών / σειρών στην λίστα με τα
αγαπημένα τους. Επομένως, στα αποτελέσματα αναζήτησης και στην προβολή μιας ταινίας/σειράς,
θα πρέπει να υπάρχει αντίστοιχη επιλογή προσθήκης ή αφαίρεσης από την λίστα.
Ζ. Σύνοψη λειτουργιών διαχειριστών
[Ζ1] Προσθήκη ταινίας/σειράς/σεζόν/επεισοδίων
[Ζ2] Επεξεργασία ταινίας/σειράς/σεζόν/επεισοδίων
[Ζ3] Διαγραφή ταινίας/σειράς/σεζόν/επεισοδίων
[Ζ4] Αναζήτηση ταινιών και σειρών

Σημειώσεις
● Κατά την εισαγωγή/επεξεργασία θα πρέπει να υπάρχει η δυνατότητα χρήσης όλων των χα-
ρακτηριστικών που παρουσιάστηκαν στις ενότητες Α. Καταχώρηση ταινίας και Β. Καταχώ-
ρηση σειράς.
● Οι διαχειριστές δεν μπορούν να επεξεργαστούν τις αξιολογήσεις.
● Κατά την διαγραφή μιας ταινίας/σειράς, θα πρέπει να σβήνεται από τα αγαπημένα των χρη-
στών.

Η. Σύνοψη λειτουργιών Συνδρομητών
[Η1] Αναζήτηση ταινιών και σειρών
[Η2] Εισαγωγή αξιολόγησης για ταινία/σειρά.
[Η3] Επεξεργασία αξιολόγησης.
[Η4] Διαγραφή αξιολόγησης.
[Η5] Καρτέλα με τα Αγαπημένα: Οι ταινίες/σειρές θα πρέπει να εμφανίζονται π.χ. σε μια λίστα
με μόνο κάποιες βασικές πληροφορίες, π.χ. τίτλος και τύπος. Επιλέγοντας ένα αποτέλεσμα
θα πρέπει να εμφανίζεται η καρτέλα της Προβολής (Γ. Προβολή ταινίας / σειράς).

Σημειώσεις
• Θα πρέπει να υπάρχουν κατάλληλα μηνύματα στο GUI σε περίπτωση που υπάρχει κάποιο
σφάλμα.
Θ. Εγγραφή συνδρομητών
Φόρμα εγγραφής συνδρομητών στην πλατφόρμα (registration):
[Θ1] Όνομα
[Θ2] Επίθετο
[Θ3] Username
[Θ4] Password
(*Για λόγους απλούστευσης της εργασίας, δεν υπάρχει συνδρομή με την έννοια της πληρωμής, απλά
η δημιουργία ενός λογαριασμού στο σύστημα ισοδυναμεί με συνδρομή)

Σημειώσεις
• Δεν θα πρέπει να επιτρέπεται η εγγραφή κάποιου χρήστη με username το οποίο ήδη υπάρχει.
• Όλα τα πεδία είναι υποχρεωτικά.
• Δεν γίνεται να δημιουργηθεί ένας λογαριασμός μέσω της φόρμας για διαχειριστή (οι διαχει-
ριστές είναι προκαθορισμένοι, βλ. ΙΑ. Αρχικοποίηση προγράμματος)
• Θα πρέπει να υπάρχουν κατάλληλα μηνύματα στο GUI σε περίπτωση που υπάρχει κάποιο
σφάλμα, π.χ. όταν υπάρχει ήδη το username.

Ι. Σύνδεση / αποσύνδεση χρηστών
[Ι1] Φόρμα σύνδεσης διαχειριστών και χρηστών στην πλατφόρμα (login) με username και pass-
word. Δεν πρέπει να υπάρχει επιλογή στην φόρμα του τύπου του χρήστη που συνδέεται.

Ανάλογα με τα credentials, το πρόγραμμα θα πρέπει να καταλαβαίνει ποιος συνδέεται.
[Ι2] Επιλογή αποσύνδεσης χρήστη.

ΙΑ. Αρχικοποίηση προγράμματος
Μέσω της αρχικοποίησης, πρέπει υποχρεωτικά να υπάρχουν τα παρακάτω:
[ΙΑ1]Τουλάχιστον 2 διαχειριστές με:
o Username: admin1, password: password1
o Username: admin2, password: password2
[ΙΑ2]Τουλάχιστον 2 συνδρομητές με:
o Username: user1, password: password1
o Username: user2, password: password2
[ΙΑ3]>= 5 ταινίες και >=5 σειρές με πληροφορία σε όλα τα χαρακτηριστικά (οι τιμές να μην είναι
ίδιες στα χαρακτηριστικά για να έχει νόημα η αναζήτηση) που θα μοιραστούν στους 2 χρη-
στές παραπάνω. Κάθε σειρά θα πρέπει να έχει >=2 σεζόν και κάθε σεζόν >=3 επεισόδια με
τιμές σε όλες τα χαρακτηριστικά τους.
[ΙΑ4]>= 2 αξιολογήσεις σε κάθε ταινία και σειρά που θα μοιραστούν στους 2 παραπάνω χρήστες.
[ΙΑ5]Κάθε συνδρομητής θα πρέπει να έχει τουλάχιστον μια καταχώρηση στα Αγαπημένα.

Σημειώσεις
• Η αρχικοποίηση θα πρέπει να γίνεται μέσα από αρχεία.
• Αν κατά την διάρκεια εκτέλεσης του προγράμματος γίνουν αλλαγές, π.χ. προστεθεί μια αξιο-
λόγηση, μια ταινία, κτλ., αυτές οι αλλαγές πρέπει να αποθηκευτούν, έτσι ώστε όταν κλείσει
και ξανά ανοίξει το πρόγραμμα, να είναι διαθέσιμες.

ΙΒ. Μη Λειτουργικές Απαιτήσεις και Διευκρινήσεις
[ΙΒ1] Το πρόγραμμα πρέπει να υλοποιηθεί στο IntelliJ project του github repository που θα δη-
μιουργηθεί για την ομάδα όταν γίνει accept το assignment (όπως κάνουμε και στα εργαστή-
ρια), και όχι σε εξωτερικά repositories που δεν έχουν δημιουργηθεί μέσα από την προανα-
φερθείσα διαδικασία.
[ΙΒ2] Είναι απαραίτητο να ελέγξετε ότι το πρόγραμμα είναι λειτουργικό (να κάνει compile και να
τρέχει) μετά από ένα «καθαρό» clone. Προτείνεται να κάνετε ένα clone το τελικό repository
στο IntelliJ σε έναν υπολογιστή που δεν έχει χρησιμοποιηθεί στην υλοποίηση (π.χ. σε κά-
ποιον υπολογιστή στο PC Lab), ώστε να γίνουν οι απαραίτητοι έλεγχοι, για παράδειγμα ότι
μπορεί να διαβάσει τα αρχεία που έχετε κάνει, κτλ. Προγράμματα τα οποία δεν κάνουν
compile δεν θα βαθμολογηθούν.
[ΙΒ3] Σε περίπτωση που χρησιμοποιήσετε εξωτερικές βιβλιοθήκες, πρέπει να εξασφαλίσετε ότι
είναι μέρος του github repository της εργασίας σας (να είναι μέρος των commits που θα
κάνετε), ώστε όταν γίνει clone σε διαφορετικό μηχάνημα να μπορεί το IDE να τις βρει.
[ΙΒ4] Είναι απαραίτητο να φαίνεται το ιστορικό της υλοποίησης (commits) και από τους 2 προ-
γραμματιστές. Οπότε και οι δύο πρέπει να δουλεύουν (συνεργατικά) στο ίδιο github repos-
itory [ΙΒ1]. Συνεργατικά δεν σημαίνει ότι πρέπει και οι δύο να δουλεύουν μαζί στον ίδιο
χώρο ή στον ίδιο υπολογιστή. Μπορεί να γίνει αρχικά ένας διαμοιρασμός των απαιτήσεων
και της υλοποίησης, και ο καθένας / καθεμία να δουλεύει ανεξάρτητα.
[ΙΒ5]Πρέπει να υπάρχουν tests για όλες τις κλάσεις/μεθόδους, εκτός αυτών που ασχολούνται με
την υλοποίηση του GUI. Για να έχετε μια εικόνα του ποσοστού του κώδικα που ελέγχεται
μέσω tests, μπορείτε να τρέξετε τα tests από την επιλογή “Run All Tests with Coverage” (η
επιλογή υπάρχει με δεξί κλικ στον φάκελο με τα tests). Θα εμφανιστούν ποσοστά για τις
κλάσεις και τις μεθόδους (δεν χρειάζεται για γραμμές κώδικα). Οι κλάσεις για GUI (πακέτο
gui) πρέπει να εξαιρεθούν για μεγαλύτερη ακρίβεια (εδώ βοηθάει το ότι θα πρέπει να υ-
πάρχουν 2 root packages για την λογική και την διασύνδεση, είναι μέρος του αρχικού σκε-
λετού του project που δημιουργείται. Μπορείτε να δημιουργήσετε όσα πακέτα θέλετε κάτω
από τα api και gui για να οργανώσετε περαιτέρω τον κώδικά σας). Τα test δεν μπορούν να
τρέξουν στο GitHub, θα τρέχουν μόνο τοπικά, οπότε μην περιμένετε μετά από push να βγει
κάποιο x ή ✓, όπως γίνεται στα εβδομαδιαία εργαστήρια.
[ΙΒ6]Ο κώδικας θα πρέπει να είναι επαρκώς τεκμηριωμένος με χρήση Javadoc (σχόλια σε επίπεδο
κλάσεων, ιδιοτήτων, μεθόδων, παραμέτρων και επιστρεφόμενων τιμών). Δεν χρειάζεται
Javadoc σε κλάσεις που υλοποιούν το GUI.
[ΙΒ7] CodeMR: επικεντρωθείτε μόνο στις προβληματικές κλάσεις (όχι σε όλες τις μετρικές) όλου
του project, εξαιρώντας βέβαια τις κλάσεις ελέγχου.
[ΙΒ8] Είναι απαραίτητο να υπάρχουν τα usernames και τα passwords για τους χρήστες όπως αυτά
περιγράφονται στα [ΙΑ1] και [ΙΑ2].
[ΙΒ9] Είναι απαραίτητο το πρόγραμμά σας να αρχικοποιείται με επαρκή δεδομένα απαραίτητα
για τον έλεγχο όλων των λειτουργιών ([ΙΑ3] και [ΙΑ4]).



